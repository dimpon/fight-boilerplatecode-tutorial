package com.dimpon.tutorals.lombok.delegation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

/**
 * @author Dmitrii Ponomarev
 */

public class LazyStaticFieldThreadSafe<I,C>  {
	private  Supplier<C> supplier;

	private Class<? extends I> realClass;

    public void setSupplier(Supplier<C> supplier) {
        this.supplier = supplier;
    }



   /*private I createRealObject() {
		return supplier.get();
	}*/

    @SuppressWarnings("unchecked")
	public I getProxy() {

        return  (I)Proxy.newProxyInstance(
                LazyStaticFieldThreadSafe.class.getClassLoader(),
                new Class[]{getGenericClass()},
                new DynamicInvocationHandler<C>(supplier));

    }

	@SuppressWarnings("unchecked")
	private Class<C> getGenericClass() {
		Class actualClass = this.getClass();
		ParameterizedType type = (ParameterizedType) actualClass.getGenericSuperclass();
		System.out.println(type); // java.util.ArrayList<java.lang.Float>
		return (Class<C>) type.getActualTypeArguments()[0];
	}

	// @Getter(lazy = true, onMethod_ = { @Delegate})
	// public final K logger = createRealObject();

	public static class DynamicInvocationHandler<T> implements InvocationHandler {

        private Supplier<T> supplier;

        private T object = null;

        public DynamicInvocationHandler(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        @Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("ready to invoke");
            if(object==null)
                object = supplier.get();

            return method.invoke(object, args);

		}
	}

    public static void main(String[] args) {

        LazyStaticFieldThreadSafe<HelloDolly,HelloDollyImpl> factory = new LazyStaticFieldThreadSafe<HelloDolly,HelloDollyImpl>(){};
        factory.setSupplier(() -> {
            System.out.println("create real dolly");
            return new HelloDollyImpl();});


        Supplier<HelloDolly> d = HelloDollyImpl::new;


        HelloDolly realObject = factory.getProxy();
        System.out.println("********");
        realObject.hello();


    }

    /////////////

    interface HelloDolly{
	    void hello();
    }

    public static class HelloDollyImpl implements HelloDolly{

        public HelloDollyImpl() {
            System.out.println("construct dolly");
        }

        @Override
        public void hello() {
            System.out.println("sayHello");
        }
    }

}
