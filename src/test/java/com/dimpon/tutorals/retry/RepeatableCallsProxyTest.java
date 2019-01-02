package com.dimpon.tutorals.retry;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Mockito.*;

/**
 * @author Dmitrii Ponomarev
 */
@RunWith(MockitoJUnitRunner.class)
public class RepeatableCallsProxyTest {

    interface Schmetterling {
        void fliegen();

        int pfoten();
    }

    @Test(expected = NullPointerException.class)
    public void testFailBothMethods() {

        Schmetterling mocked = mock(Schmetterling.class);

        when(mocked.pfoten())
                .thenThrow(new NullPointerException())
                .thenThrow(new IllegalStateException("no service"))
                .thenReturn(42);

        doThrow(new NullPointerException()).when(mocked).fliegen();

        Schmetterling schmetterling = RepeatableCallsProxy.<Schmetterling> builder()
                .interfaceClass(Schmetterling.class)
                .supplier(() -> mocked)
                .exception(NullPointerException.class)
                .exception(IllegalStateException.class)
                .attempts(3)
                .build()
                .proxy();

        schmetterling.pfoten();
        schmetterling.pfoten();
        schmetterling.fliegen();

    }

    @Test
    public void testSimpleCounterWorksForAllMethodsOfService() {

        Schmetterling mocked = mock(Schmetterling.class);

        when(mocked.pfoten())
                .thenThrow(new NullPointerException())
                .thenThrow(new NullPointerException())
                .thenThrow(new NullPointerException())
                .thenReturn(42);

        doThrow(new NullPointerException()).when(mocked).fliegen();

        Schmetterling schmetterling = RepeatableCallsProxy.<Schmetterling> builder()
                .interfaceClass(Schmetterling.class)
                .supplier(() -> mocked)
                .exception(NullPointerException.class)
                .exception(IllegalStateException.class)
                .attempts(3)
                .build()
                .proxy();

        // every 3rd call re-throw exception. then, counter is re-set.

        // 3 times and then throw
        methodThrowsCommunicationException(schmetterling::pfoten);

        // 4th time is ok
        int pfoten = schmetterling.pfoten();
        Assert.assertEquals(42, pfoten);

        // again 3 times and then throw
        methodThrowsCommunicationException(schmetterling::fliegen);

        verify(mocked, times(4)).pfoten();
        verify(mocked, times(3)).fliegen();

    }

    @Test
    public void testThreadsOneMethodFails() throws Exception {

        Schmetterling mocked = mock(Schmetterling.class);

        when(mocked.pfoten())
                .thenReturn(42);

        // method fliegen() fails
        doThrow(new NullPointerException()).when(mocked).fliegen();

        Schmetterling schmetterling = RepeatableCallsProxy.<Schmetterling> builder()
                .interfaceClass(Schmetterling.class)
                .supplier(() -> mocked)
                .exception(NullPointerException.class)
                .exception(IllegalStateException.class)
                .attempts(3)
                .timeout(1)
                .build()
                .proxy();

        final CountDownLatch latch = new CountDownLatch(200);

        final ExecutorService executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                methodThrowsCommunicationException(schmetterling::fliegen);
                latch.countDown();
            });
            executor.execute(() -> {
                schmetterling.pfoten();
                latch.countDown();
            });
        }

        latch.await(1, TimeUnit.MINUTES);

        verify(mocked, times(100)).pfoten();
        verify(mocked, times(300)).fliegen();

    }

    @Test
    public void testThreadsBothMethodFails() throws Exception {

        Schmetterling mocked = mock(Schmetterling.class);

        // method pfoten() fails
        when(mocked.pfoten()).thenThrow(new NullPointerException());

        // method fliegen() fails
        doThrow(new NullPointerException()).when(mocked).fliegen();

        Schmetterling schmetterling = RepeatableCallsProxy.<Schmetterling> builder()
                .interfaceClass(Schmetterling.class)
                .supplier(() -> mocked)
                .exception(NullPointerException.class)
                .exception(IllegalStateException.class)
                .attempts(5)
                .timeout(1)
                .build()
                .proxy();

        final CountDownLatch latch = new CountDownLatch(200);

        final ExecutorService executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                methodThrowsCommunicationException(schmetterling::fliegen);
                latch.countDown();
            });
            executor.execute(() -> {
                methodThrowsCommunicationException(schmetterling::pfoten);
                latch.countDown();
            });
        }

        latch.await(1, TimeUnit.MINUTES);

        verify(mocked, times(500)).pfoten();
        verify(mocked, times(500)).fliegen();
    }

    @Test
    public void testThreadsBothMethodSuccessful() throws Exception {

        Schmetterling mocked = mock(Schmetterling.class);

        when(mocked.pfoten())
                .thenReturn(42);

        Schmetterling schmetterling = RepeatableCallsProxy.<Schmetterling> builder()
                .interfaceClass(Schmetterling.class)
                .supplier(() -> mocked)
                .exception(NullPointerException.class)
                .exception(IllegalStateException.class)
                .attempts(5)
                .timeout(1)
                .build()
                .proxy();

        final CountDownLatch latch = new CountDownLatch(200);

        final ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                schmetterling.fliegen();
                latch.countDown();
            });
            executor.execute(() -> {
                int pfoten = schmetterling.pfoten();
                Assert.assertEquals(42, pfoten);
                latch.countDown();
            });
        }

        latch.await(1, TimeUnit.MINUTES);

        verify(mocked, times(100)).pfoten();
        verify(mocked, times(100)).fliegen();

    }

    private void methodThrowsCommunicationException(Runnable method) {
        try {
            method.run();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertThat(e, instanceOf(NullPointerException.class));
        }
    }

    @Test
    public void testSimple() {

        Schmetterling mocked = mock(Schmetterling.class);
        Supplier<Schmetterling> supplierMock = mock(Supplier.class);
        when(supplierMock.get()).thenReturn(mocked);

        when(mocked.pfoten())
                .thenThrow(new NullPointerException())
                .thenThrow(new IllegalStateException("no service"))
                .thenReturn(42);

        Schmetterling schmetterling = RepeatableCallsProxy.<Schmetterling> builder()
                .interfaceClass(Schmetterling.class)
                .supplier(supplierMock)
                .exception(NullPointerException.class)
                .exception(IllegalStateException.class)
                .build()
                .proxy();

        int pfoten = schmetterling.pfoten();

        Assert.assertEquals(42, pfoten);
        verify(mocked, times(3)).pfoten();
        verify(supplierMock, times(3)).get();

    }

    @Test
    public void testCallingIntervals() {

        final long timeout = TimeUnit.SECONDS.toMillis(1);
        final List<Long> attemptsRegister = new ArrayList<>();

        Schmetterling mocked = mock(Schmetterling.class);

        when(mocked.pfoten())
                .then(invocation -> {
                    attemptsRegister.add(System.currentTimeMillis());
                    throw new NullPointerException();
                })
                .then(invocation -> {
                    attemptsRegister.add(System.currentTimeMillis());
                    throw new NullPointerException();
                })
                .then(invocation -> {
                    attemptsRegister.add(System.currentTimeMillis());
                    throw new IllegalStateException("no service");
                })
                .then(invocation -> {
                    attemptsRegister.add(System.currentTimeMillis());
                    return 42;
                });

        Schmetterling schmetterling = RepeatableCallsProxy.<Schmetterling> builder()
                .interfaceClass(Schmetterling.class)
                .supplier(() -> mocked)
                .exception(NullPointerException.class)
                .exception(IllegalStateException.class)
                .timeout(timeout)
                .attempts(10)
                .build()
                .proxy();

        int pfoten = schmetterling.pfoten();

        for (int i = 0; i < attemptsRegister.size() - 1; i++) {
            Long first = attemptsRegister.get(i);
            Long second = attemptsRegister.get(i + 1);
            Assert.assertTrue("The next attempt must go after timeout", ((second - first) >= 1_000));
        }

        Assert.assertEquals(42, pfoten);
        verify(mocked, times(4)).pfoten();

    }


}
