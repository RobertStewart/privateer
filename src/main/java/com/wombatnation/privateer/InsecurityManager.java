package com.wombatnation.privateer;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.security.Permission;

/**
 * A completely unsecure, and possibly a little insecure, SecurityManager.
 * Installing this SecurityManager allows Privateer to access fields and
 * methods that are normally inaccessible.
 */
public class InsecurityManager extends SecurityManager {
  @Override
  public void checkAccept(String host, int port) {
//    super.checkAccept(host, port);
  }

  @Override
  public void checkAccess(Thread t) {
//    super.checkAccess(t);
  }

  @Override
  public void checkAccess(ThreadGroup g) {
//    super.checkAccess(g);
  }

  @Override
  public void checkAwtEventQueueAccess() {
//    super.checkAwtEventQueueAccess();
  }

  @Override
  public void checkConnect(String host, int port, Object context) {
//    super.checkConnect(host, port, context);
  }

  @Override
  public void checkConnect(String host, int port) {
//    super.checkConnect(host, port);
  }

  @Override
  public void checkCreateClassLoader() {
//    super.checkCreateClassLoader();
  }

  @Override
  public void checkDelete(String file) {
//    super.checkDelete(file);
  }

  @Override
  public void checkExec(String cmd) {
//    super.checkExec(cmd);
  }

  @Override
  public void checkExit(int status) {
//    super.checkExit(status);
  }

  @Override
  public void checkLink(String lib) {
//    super.checkLink(lib);
  }

  @Override
  public void checkListen(int port) {
//    super.checkListen(port);
  }

  @Override
  public void checkMemberAccess(Class<?> clazz, int which) {
//    super.checkMemberAccess(clazz, which);
  }

  @Override
  public void checkMulticast(InetAddress maddr) {
//    super.checkMulticast(maddr);
  }

  @Override
  public void checkPackageAccess(String pkg) {
//    super.checkPackageAccess(pkg);
  }

  @Override
  public void checkPackageDefinition(String pkg) {
//    super.checkPackageDefinition(pkg);
  }

  @Override
  public void checkPermission(Permission perm, Object context) {
//    super.checkPermission(perm, context);
  }

  @Override
  public void checkPermission(Permission perm) {
//    super.checkPermission(perm);
  }

  @Override
  public void checkPrintJobAccess() {
//    super.checkPrintJobAccess();
  }

  @Override
  public void checkPropertiesAccess() {
//    super.checkPropertiesAccess();
  }

  @Override
  public void checkPropertyAccess(String key) {
//    super.checkPropertyAccess(key);
  }

  @Override
  public void checkRead(FileDescriptor fd) {
//    super.checkRead(fd);
  }

  @Override
  public void checkRead(String file, Object context) {
//    super.checkRead(file, context);
  }

  @Override
  public void checkRead(String file) {
//    super.checkRead(file);
  }

  @Override
  public void checkSecurityAccess(String target) {
//    super.checkSecurityAccess(target);
  }

  @Override
  public void checkSetFactory() {
//    super.checkSetFactory();
  }

  @Override
  public void checkSystemClipboardAccess() {
//    super.checkSystemClipboardAccess();
  }

  @Override
  public boolean checkTopLevelWindow(Object window) {
//    return super.checkTopLevelWindow(window);
    return true;
  }

  @Override
  public void checkWrite(FileDescriptor fd) {
//    super.checkWrite(fd);
  }

  @Override
  public void checkWrite(String file) {
//    super.checkWrite(file);
  }
}
