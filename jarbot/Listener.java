package jarbot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class Listener implements EventListener {

	int cont = 0;
	String hola = "hola";
	static List<String> automensajes = new ArrayList<String>();

	@Override
	public void onEvent(Event event) {
		System.out.println(event.getClass().getSimpleName());

		if (event instanceof MessageReceivedEvent) {
			onMessage((MessageReceivedEvent) event);
		}
	}

	private void onMessage(MessageReceivedEvent event) {
		if (event.getAuthor().equals(event.getJDA().getSelfUser())) {
			automensajes.add(event.getMessageId());
			return;
		}

		String mensaje = event.getMessage().getContentDisplay();
		if (mensaje.contains("!jarbot")) {
			command(event, mensaje);
		}

	}

	public void command(MessageReceivedEvent event, CharSequence mensaje) {
		if (((String) mensaje).contains("ping")) {
			long ping = event.getJDA().getPing();
			event.getTextChannel().sendMessage("Tengo " + ping + " de ping").complete();
		} else if (((String) mensaje).contains("help")) {
			String help = leerFichero();
			event.getTextChannel().sendMessage(help)
					.append(". /n Visita y mejora el código en www.github.com/parktem/jarbot").complete();
		} else if (((String) mensaje).contains("delete")) {
			event.getTextChannel().deleteMessageById(automensajes.get(automensajes.size() - 1));
			System.out.println(automensajes.get(automensajes.size() - 1));
			System.out.println("holi");
		} else {
			event.getTextChannel()
					.sendMessage(
							"No reconozco este comando, lo siento, echa un vistazo a los comandos con '!jarbot help'")
					.complete();
		}

	}

	public String leerFichero() {

		try {
			FileReader fr = new FileReader("C:\\Users\\Alejandro\\Desktop\\help.txt");
			// Debes reemplazar nombre_de_usuario por tu nombre de usuario
			BufferedReader bf = new BufferedReader(fr);
			String cad;
			try {
				String help = "";
				while ((cad = bf.readLine()) != null) {
					help += cad;
				}
				return help;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		} catch (FileNotFoundException e) {
			System.out.println("Archivo desconocido");
		}
		return null;

	}

}
