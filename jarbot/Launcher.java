package jarbot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Launcher {

	public static void main(String[] args) {
		try {
			JDA jda = new JDABuilder(AccountType.BOT)
					.setToken("NDAwNzUzMTIyODk4NzM5MjIw.Dfp3_g.X53IMIUb_s1b1sAlelMpImJ9IGk").buildAsync();
			jda.addEventListener(new Listener());
		} catch (LoginException e) {

		}
	}

}
