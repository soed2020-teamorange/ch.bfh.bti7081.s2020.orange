package ch.bfh.bti7081.s2020.orange.ui.utils;

public interface ViewWithObserver<T> extends View {

  void setObserver(T observer);
}
