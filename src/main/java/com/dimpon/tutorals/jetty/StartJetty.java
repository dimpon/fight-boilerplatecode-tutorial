package com.dimpon.tutorals.jetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.servlet.DefaultServlet;

import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.EnumSet;

public class StartJetty
{
    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);

        context.setContextPath("/");
        //context.setResourceBase(System.getProperty("java.io.tmpdir"));


        context.addServlet(EmptyServlet.class, "/");
        context.addFilter(HalloFilter.class,"/", EnumSet.of(DispatcherType.REQUEST));

        HandlerCollection handlerCol = new HandlerCollection();
        handlerCol.addHandler(context);
        handlerCol.addHandler(new HelloHandler());

        server.setHandler(handlerCol);

        //HandlerCollection handlerCol = new HandlerCollection();
        //handlerCol.addHandler(context);
        //handlerCol.addHandler(new HelloHandler());
        //handlerCol.addHandler(new RequestLogHandler());

        //handlerCol.setHandlers(new Handler[] { context, new HelloHandler() });

        //handlerCol.setHandlers(new HandlerList(new Handler[] { context, new HelloHandler() });

        /*HandlerList handlerList = new HandlerList();
        handlerList.addHandler(context);
        handlerList.addHandler(new HelloHandler());*/




        //server.setHandler(new HelloHandler());

        // Add dump servlet
        /*context.addServlet(
                context.addServlet(DumpServlet.class, "/dump/*"),
                "*.dump");
        context.addServlet(HelloServlet.class, "/hello/*");*/
        //context.addServlet(DefaultServlet.class, "/");






        //context.addFilter(TestFilter.class,"/test", EnumSet.of(DispatcherType.REQUEST,DispatcherType.ASYNC));
        //context.addFilter(TestFilter.class,"*.test", EnumSet.of(DispatcherType.REQUEST,DispatcherType.INCLUDE,DispatcherType.FORWARD));

     /*   context.getServletHandler().addListener(new ListenerHolder(InitListener.class));
        context.getServletHandler().addListener(new ListenerHolder(RequestListener.class));
*/
        server.start();
        server.dumpStdErr();
        server.join();
    }





}