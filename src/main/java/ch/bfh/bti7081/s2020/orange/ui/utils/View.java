package ch.bfh.bti7081.s2020.orange.ui.utils;

import com.vaadin.flow.component.HasComponents;

public interface View extends HasComponents {

  <C> C getComponent(Class<C> type);
}
