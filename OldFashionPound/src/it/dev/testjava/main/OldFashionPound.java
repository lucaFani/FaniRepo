package it.dev.testjava.main;

import java.util.regex.Pattern;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import it.dev.testjava.bo.OperazioneT;
import it.dev.testjava.bo.Prezzo;
import it.dev.testjava.exception.TestJavaException;
import it.dev.testjava.util.OldFashionPoundUtil;

public class OldFashionPound {
	static final Logger LOG = Logger.getLogger(OldFashionPound.class);
	public static int errorCode = 8;
	public static int exitCode = 0;

	/**
	 * Costruisce la command-line di richiesta stampa help.
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Options getCmdlineHelpOptions() {
		Options options = new Options();

		options.addOption(OptionBuilder.isRequired(false).hasArg(false).withLongOpt("help").withDescription("Help").create("?"));
		return options;
	}

	/**
	 * Costruisce la lista dei parametri accettati in input, con le varie regole
	 * di obbligatorietà, argomenti richiesta, ...
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Options getCmdlineOptions() {
		Options options = new Options();

		options.addOption(OptionBuilder.isRequired(false).hasArg(false).withLongOpt("help").withDescription("Help").create("?"));

		options.addOption(OptionBuilder.isRequired(true).hasArg(true).withArgName("op").withLongOpt("operazione").withDescription("Operazione: \n +=addizione, -=sottrazione, x=moltiplicazione, :=divisione. \n Esempio: -op +").withType(String.class).create("op"));

		options.addOption(OptionBuilder.isRequired(true).hasArg(true).withArgName("p1").withLongOpt("prezzo1").withDescription("Prezzo1 nel formato 'Xp Ys Zd' Esempio: -p1 5p 17s 8d").withType(String.class).create("p1"));

		options.addOption(OptionBuilder.isRequired(true).hasArg(true).withArgName("p2").withLongOpt("prezzo2").withDescription("Prezzo2 nel formato 'Xp Ys Zd' per \n ADDIZIONI e SOTTRAZIONI \n oppure un intero per \n le MOLTIPLICAZIONI e DIVISIONI. \n Esempi: -p2 5p 17s 8d o -p2 17").withType(String.class).create("p2"));

		return options;
	}

	/**
	 * Verifica se il batch è stato invocato con il solo parametro "-?" per
	 * ottenere l'help sui parametri di invocazione.
	 * 
	 * @param args
	 * @return
	 */
	public static boolean isHelp(String[] args) {
		try {
			CommandLineParser parser = new GnuParser();
			Options helpOptions = getCmdlineHelpOptions();
			CommandLine cmdLine = parser.parse(helpOptions, args, false);
			if (cmdLine.hasOption("?")) {
				printHelp();
				return true;
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
		}

		return false;
	}

	/**
	 * Scrive su standard out l'help sui parametri di invocazione.
	 */
	public static void printHelp() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("OldFashionPound cmdLineOptions: ", getCmdlineOptions(), true);
	}

	public static void main(String[] args) {
		Options options = getCmdlineOptions();
		CommandLineParser parser = new GnuParser();
		boolean printHelpOnError = true;
		boolean isError = false;

		try {
			if (isHelp(args)) {
				return;
			}
			CommandLine cmdLine = parser.parse(options, args);

			// parametri
			String pOperazione = cmdLine.getOptionValue("op");
			String pPrezzo1 = cmdLine.getOptionValue("p1");
			String pPrezzo2 = cmdLine.getOptionValue("p2");

			/******** Input Validate *********************/
			Prezzo prezzo1 = null;
			Prezzo prezzo2 = null;
			int prezzo2Intero = 0;

			if (pOperazione == null || "".equals(pOperazione) || pPrezzo1 == null || "".equals(pPrezzo1) || pPrezzo2 == null || "".equals(pPrezzo2)) {
				throw new TestJavaException("Parametri di input obbligatori non valorizzati");
			}

			final String regex = "\\d{1,9}[p]\\s\\d{1,9}[s]\\s\\d{1,9}[d]";

			final Pattern pattern = Pattern.compile(regex);

			if (!pattern.matcher(pPrezzo1).find()) {
				throw new TestJavaException("Formato Prezzo1 di input errato. \n Esempio corretto: Xp Ys Zd");
			} else {
				String[] ss = pPrezzo1.split(" ");
				Integer p = Integer.parseInt(ss[0].replace("p", "").trim());
				Integer s = Integer.parseInt(ss[1].replace("s", "").trim());
				Integer d = Integer.parseInt(ss[2].replace("d", "").trim());
				prezzo1 = Prezzo.creaPrezzo(p.intValue(), s.intValue(), d.intValue());
			}

			OperazioneT op = (pOperazione.equals(OperazioneT.ADDIZIONE.getValue()) ? OperazioneT.ADDIZIONE : pOperazione.equals(OperazioneT.SOTTRAZIONE.getValue()) ? OperazioneT.SOTTRAZIONE : pOperazione.equals(OperazioneT.MOLTIPLICAZIONE.getValue()) ? OperazioneT.MOLTIPLICAZIONE : pOperazione.equals(OperazioneT.DIVISIONE.getValue()) ? OperazioneT.DIVISIONE : null);

			if (op == null) {
				throw new TestJavaException("Operazione non gestita.");
			} else {
				if (op.equals(OperazioneT.ADDIZIONE) || op.equals(OperazioneT.SOTTRAZIONE)) {
					if (!pattern.matcher(pPrezzo2).find()) {
						throw new TestJavaException("Formato Prezzo2 di input errato. \n Esempio corretto: Xp Ys Zd");
					} else {
						String[] ss = pPrezzo2.split(" ");
						Integer p = Integer.parseInt(ss[0].replace("p", "").trim());
						Integer s = Integer.parseInt(ss[1].replace("s", "").trim());
						Integer d = Integer.parseInt(ss[2].replace("d", "").trim());
						prezzo2 = Prezzo.creaPrezzo(p.intValue(), s.intValue(), d.intValue());
					}
				} else {
					if (!StringUtils.isNumeric(pPrezzo2.trim())) {
						throw new TestJavaException("Formato Prezzo2 di input errato. Deve essere un intero.");
					} else {
						prezzo2Intero = Integer.parseInt(pPrezzo2.trim());
					}
				}
			}

			// VALIDATE INPUT :: OK! :: 
			printHelpOnError = false;

			Prezzo prezzoFinale = null;

			switch (op) {
			case ADDIZIONE:
				prezzoFinale = OldFashionPoundUtil.add(prezzo1, prezzo2);
				OldFashionPound.LOG.info(prezzo1 + " + " + prezzo2 + " = " + prezzoFinale);
				break;
			case SOTTRAZIONE:
				prezzoFinale = OldFashionPoundUtil.subtract(prezzo1, prezzo2);
				OldFashionPound.LOG.info(prezzo1 + " - " + prezzo2 + " = " + prezzoFinale);
				break;
			case MOLTIPLICAZIONE:
				prezzoFinale = OldFashionPoundUtil.multiply(prezzo1, prezzo2Intero);
				OldFashionPound.LOG.info(prezzo1 + " x " + prezzo2Intero + " = " + prezzoFinale);
				break;
			case DIVISIONE:
				prezzoFinale = OldFashionPoundUtil.divide(prezzo1, prezzo2Intero);
				OldFashionPound.LOG.info(prezzo1 + " : " + prezzo2Intero + " = " + prezzoFinale);
				break;
			}

		} catch (Exception e) {
			isError = true;
			if (printHelpOnError) {
				printHelp();
			}
			OldFashionPound.LOG.error("main - Esecuzione fallita. ");
			OldFashionPound.LOG.error("main - " + e.getMessage());

		} finally {

			if (isError) {
				System.exit(errorCode);
			} else {
				System.exit(exitCode);
			}
		}

	}

}
