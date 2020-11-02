package framework.context;


import framework.scanners.ApplicationScanner;
import framework.containers.ObjectContainer;
import framework.factory.ObjectFactory;
import framework.scanners.DefaultApplicationScanner;
import lombok.SneakyThrows;

public class DefaultApplicationContext implements ApplicationContext {
    private String packageScan;
    private ApplicationScanner scanner;
    private ApplicationScanner userScanner;

    private ObjectContainer objectContainer;
    private ObjectFactory objectFactory;

    public DefaultApplicationContext() { }

    public DefaultApplicationContext(String packageScan) {
        this(packageScan, null);
    }

    public DefaultApplicationContext(String packageScan, ApplicationScanner scanner) {
        this.packageScan = packageScan;
        this.userScanner = scanner;
        refresh();
    }

    public void setScanner(ApplicationScanner scanner) {
        this.userScanner = scanner;
    }

    public void setPackageScan(String packageScan) {
        this.packageScan = packageScan;
    }

    @Override
    public void refresh() {
        reset();
        scanner = getApplicationScanner();
        objectContainer = getObjectContainer();
        objectFactory = getObjectFactory();
        configureFactory();
    }

    private void reset() {
        this.objectContainer = null;
        this.objectFactory = null;
    }

    private void configureFactory() {
        objectFactory.initializeFactory();
    }

    protected ApplicationScanner getApplicationScanner() {
        if(userScanner == null) return new DefaultApplicationScanner(packageScan);
        return userScanner;
    }

    @SneakyThrows
    protected ObjectContainer getObjectContainer() {
        return scanner.findApplicationObjectContainer().getConstructor().newInstance();
    }

    @SneakyThrows
    protected ObjectFactory getObjectFactory() {
        return scanner.findObjectFactory().getConstructor().newInstance();
    }
}
