package ch.bfh.bti7081.s2020.orange.application.security;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;

@FunctionalInterface
public interface CurrentUser {

  User getUser();
}
