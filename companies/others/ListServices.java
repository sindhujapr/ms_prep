package jmdns;

import java.io.IOException;

        import java.io.IOException;
        import java.util.Enumeration;
        import java.util.logging.ConsoleHandler;
        import java.util.logging.Level;
        import java.util.logging.LogManager;
        import java.util.logging.Logger;

        import javax.jmdns.JmDNS;
        import javax.jmdns.ServiceInfo;

/**
 * Sample Code for Listing Services using JmDNS.
 * <p>
 * Run the main method of this class. This class prints a list of available HTTP services every 5 seconds.
 *
 * @author Werner Randelshofer
 */
public class ListServices {

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        /* Activate these lines to see log messages of JmDNS */
        boolean log = false;
        if (log) {
            ConsoleHandler handler = new ConsoleHandler();
            handler.setLevel(Level.FINEST);
            for (Enumeration<String> enumerator = LogManager.getLogManager().getLoggerNames(); enumerator.hasMoreElements();) {
                String loggerName = enumerator.nextElement();
                Logger logger = Logger.getLogger(loggerName);
                logger.addHandler(handler);
                logger.setLevel(Level.FINEST);
            }
        }

        JmDNS jmdns = null;
        try {
            jmdns = JmDNS.create();
            while (true) {
                ServiceInfo[] infos = jmdns.list("_amzn-wplay._tcp.local.");
                System.out.println("List _airport._tcp.local.");
                for (int i = 0; i < infos.length; i++) {
                    System.out.println(infos[i]);
                }
                System.out.println();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (jmdns != null) try {
                jmdns.close();
            } catch (IOException exception) {
                //
            }
        }
    }
/*
    private void _startJmDnsService() throws IOException {

        if (mJmdns != null) {
            if (mJmDnsServiceListener != null) {
                mJmdns.removeServiceListener(mBonjourServiceType, mJmDnsServiceListener);
                mJmDnsServiceListener = null;
            }

            mJmdns.close();
            mJmdns = null;
        }

        Log.d(TAG, "starting JmDNS");
        mJmdns = JmDNS.create(getInet4Address());
        mJmDnsServiceListener = new ServiceListener() {
            public void serviceResolved(ServiceEvent ev) {
                ServiceInfo serviceInfo = ev.getInfo();
                Log.w(TAG, "serviceResolved for device " + serviceInfo.getName());
            }

            public void serviceRemoved(ServiceEvent ev) {
                ServiceInfo serviceInfo = ev.getInfo();
                Log.w(TAG, "serviceRemoved for device " + serviceInfo.getName());
                CDevice.removeDevice(deviceId);
            }

            public void serviceAdded(ServiceEvent event) {
                // Required to force serviceResolved to be called again
                // (after the first search)
                ServiceInfo serviceInfo = event.getInfo();
                Log.w(TAG, "serviceAdded for device " + serviceInfo.getName());
                mJmdns.requestServiceInfo(event.getType(), event.getName(), 1);
            }
        };

        mJmdns.addServiceListener(mBonjourServiceType, mJmDnsServiceListener);
        Log.w(TAG, "mJmdns Service Listener added!");
    }
    */
}