package ch.bfh.bti7081.s2020.orange.application.security;

import ch.bfh.bti7081.s2020.orange.ui.views.login.LoginView;

/**
 * SecurityUtils takes care of all such static operations that have to do with security and querying
 * rights from different beans of the UI.
 */
public final class SecurityUtils {

//  private SecurityUtils() {
//    // Util methods only
//  }
//
//  /**
//   * Gets the user name of the currently signed in user.
//   *
//   * @return the user name of the current user or <code>null</code> if the user has not signed in
//   */
//  public static String getUsername() {
//    SecurityContext context = SecurityContextHolder.getContext();
//    Object principal = context.getAuthentication().getPrincipal();
//    if (principal instanceof UserDetails) {
//      UserDetails userDetails = (UserDetails) context.getAuthentication().getPrincipal();
//      return userDetails.getUsername();
//    }
//    // Anonymous or no authentication.
//    return null;
//  }
//

  /**
   * Checks if access is granted for the current user for the given secured view, defined by the
   * view class.
   *
   * @param securedClass View class
   * @return true if access is granted, false otherwise.
   */
  public static boolean isAccessGranted(Class<?> securedClass) {
    // TODO
    final boolean publicView = LoginView.class.equals(securedClass);
//        || AccessDeniedView.class.equals(securedClass)
//        || CustomRouteNotFoundError.class.equals(securedClass);

    // Always allow access to public views
    if (publicView) {
      return true;
    }

    // TODO remove me
    return false;
//
//    Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
//
//    // All other views require authentication
//    if (!isUserLoggedIn(userAuthentication)) {
//      return false;
  }
//
//    // Allow if no roles are required.
//    Secured secured = AnnotationUtils.findAnnotation(securedClass, Secured.class);
//    if (secured == null) {
//      return true;
//    }
//
//    List<String> allowedRoles = Arrays.asList(secured.value());
//    return userAuthentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
//        .anyMatch(allowedRoles::contains);
//  }

  /**
   * Checks if the user is logged in.
   *
   * @return true if the user is logged in. False otherwise.
   */
  public static boolean isUserLoggedIn() {
    // TODO
    return false;
//		return isUserLoggedIn(SecurityContextHolder.getContext().getAuthentication());
  }

//	private static boolean isUserLoggedIn(Authentication authentication) {
//		return authentication != null
//			&& !(authentication instanceof AnonymousAuthenticationToken);
//	}
//
//	/**
//	 * Tests if the request is an internal framework request. The test consists of
//	 * checking if the request parameter is present and if its value is consistent
//	 * with any of the request types know.
//	 *
//	 * @param request
//	 *            {@link HttpServletRequest}
//	 * @return true if is an internal framework request. False otherwise.
//	 */
//	static boolean isFrameworkInternalRequest(HttpServletRequest request) {
//		final String parameterValue = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
//		return parameterValue != null
//				&& Stream.of(RequestType.values()).anyMatch(r -> r.getIdentifier().equals(parameterValue));
//	}

}
