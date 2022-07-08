package com.epam.cinema.command;

import com.epam.cinema.commands.CommandResolver;
import com.epam.cinema.commands.ICommand;
import com.epam.cinema.commands.open.CommandOpenMainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommandResolverTest {
    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private CommandResolver commandResolver;

    @Test
    public void givenGetCommandFromCommandResolver_whenUriIsValid_thenReturnCommand() {
        when(request.getRequestURI()).thenReturn("/cinema/main");
        ICommand iCommand = commandResolver.getCommand(request);
        assertThat(iCommand.getClass()).isEqualTo(CommandOpenMainPage.class);
    }

    @Test
    public void givenGetInstanceCommandResolver_whenInstanceIsNull_thenReturnCommandResolverInstance() {
        CommandResolver instance = CommandResolver.getInstance();
        assertThat(instance.getClass()).isEqualTo(CommandResolver.class);
    }
}
