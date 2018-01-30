package se.kwikstrom.brisket.pm.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.util.Assert;

import com.vaadin.server.VaadinSession;

public class VaadinSecurityContextHolderStrategy implements SecurityContextHolderStrategy {

	private static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

	@Override
	public void clearContext() {
		VaadinSession.getCurrent().setAttribute(SPRING_SECURITY_CONTEXT, null);
	}

	@Override
	public SecurityContext createEmptyContext() {
		return new SecurityContextImpl();
	}

	@Override
	public SecurityContext getContext() {
		SecurityContext ctx = (SecurityContext) VaadinSession.getCurrent().getAttribute(SPRING_SECURITY_CONTEXT);

		if (ctx == null) {
			ctx = createEmptyContext();
			setContext(ctx);
		}

		return ctx;
	}

	@Override
	public void setContext(SecurityContext ctx) {
		Assert.notNull(ctx, "Only non-null SecurityContext instances are permitted");
		VaadinSession.getCurrent().setAttribute(SPRING_SECURITY_CONTEXT, ctx);
	}

}
