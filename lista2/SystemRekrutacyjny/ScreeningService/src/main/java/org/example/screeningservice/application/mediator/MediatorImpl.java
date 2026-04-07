package org.example.screeningservice.application.mediator;

import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MediatorImpl implements Mediator {

    private final Map<Class<?>, CommandHandler<?, ?>> commandHandlers = new HashMap<>();
    private final Map<Class<?>, QueryHandler<?, ?>> queryHandlers = new HashMap<>();

    public MediatorImpl(ApplicationContext applicationContext) {
        // Use reflection to discover all CommandHandler beans and map them by their Command type
        String[] commandHandlerNames = applicationContext.getBeanNamesForType(CommandHandler.class);
        for (String beanName : commandHandlerNames) {
            CommandHandler<?, ?> handler = (CommandHandler<?, ?>) applicationContext.getBean(beanName);
            Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handler.getClass(), CommandHandler.class);
            if (generics != null && generics.length == 2) {
                commandHandlers.put(generics[0], handler);
            }
        }

        // Use reflection to discover all QueryHandler beans and map them by their Query type
        String[] queryHandlerNames = applicationContext.getBeanNamesForType(QueryHandler.class);
        for (String beanName : queryHandlerNames) {
            QueryHandler<?, ?> handler = (QueryHandler<?, ?>) applicationContext.getBean(beanName);
            Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handler.getClass(), QueryHandler.class);
            if (generics != null && generics.length == 2) {
                queryHandlers.put(generics[0], handler);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> R send(Command<R> command) {
        CommandHandler<Command<R>, R> handler =
                (CommandHandler<Command<R>, R>) commandHandlers.get(command.getClass());
        if (handler == null) {
            throw new IllegalArgumentException(
                    "No handler found for command: " + command.getClass().getSimpleName());
        }
        return handler.handle(command);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> R send(Query<R> query) {
        QueryHandler<Query<R>, R> handler =
                (QueryHandler<Query<R>, R>) queryHandlers.get(query.getClass());
        if (handler == null) {
            throw new IllegalArgumentException(
                    "No handler found for query: " + query.getClass().getSimpleName());
        }
        return handler.handle(query);
    }
}
