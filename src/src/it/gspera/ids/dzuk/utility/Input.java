package it.gspera.ids.dzuk.utility;

import java.io.PrintStream;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * Input si occupa di gestire l'input da uno Scanner,
 * usa un PrintStream per mostrare informazioni all'utente
 */
public class Input {
	// Devo aver già visto questa classe da qualche parte
    private Scanner scanner;
    private PrintStream output;

    public Input(Scanner scanner, PrintStream output) {
        this.scanner = scanner;
        this.output = output;
    }

    public int nextInt() {
        int v;

        while(true) {
            try {
                v = this.scanner.nextInt();
            } catch(InputMismatchException e) {
                this.output.println("Input invalido");
                this.scanner.nextLine();
                continue;
            }
            this.scanner.nextLine(); // Elimina \n lasciato da nextInt
            break;
        }

        return v;
    }
    public int nextInt(String msg) {
        this.output.println(msg);
        return this.nextInt();
    }
    public int nextInt(String msg, IntPredicate condizione, String errore) {
        this.output.println(msg);
        int v = this.nextInt();
        while(!condizione.test(v)) {
            System.out.println(errore);
            v = this.nextInt();
        }

        return v;
    }


    public String nextLine() {
        return this.scanner.nextLine();
    }
    public String nextLine(String msg) {
        this.output.println(msg);
        return this.nextLine();
    }
    public String nextLine(String msg, Predicate<String> condizione, String errore) {
        this.output.println(msg);
        String v = this.scanner.nextLine();
        while(!condizione.test(v)) {
            System.out.println(errore);
            v = this.scanner.nextLine();
        }

        return v;
    }


    public long nextLong() {
        long v;

        while(true) {
            try {
                v = this.scanner.nextLong();
            } catch(InputMismatchException e) {
                this.output.println("Input invalido");
                this.scanner.nextLine();
                continue;
            }
            this.scanner.nextLine(); // Elimina \n lasciato da nextLong
            break;
        }

        return v;
    }
    public long nextLong(String msg) {
        this.output.println(msg);
        return this.nextLong();
    }
    public long nextLong(String msg, LongPredicate condizione, String errore) {
        this.output.println(msg);
        long v = this.nextLong();
        while(!condizione.test(v)) {
            System.out.println(errore);
            v = this.nextLong();
        }

        return v;
    }

    public boolean nextBoolean() {
        boolean v;

        while(true) {
            try {
                String str = this.scanner.nextLine();
                if (str.equalsIgnoreCase("si") || str.equalsIgnoreCase("sì") || str.equalsIgnoreCase("s") || str.equalsIgnoreCase("true")) {
                    v = true;
                } else if (str.equalsIgnoreCase("no") || str.equalsIgnoreCase("n") || str.equalsIgnoreCase("false")) {
                    v = false;
                } else {
                    throw new InputMismatchException();
                }
            } catch(InputMismatchException e) {
                this.output.println("Input invalido");
                continue;
            }
            break;
        }

        return v;
    }
    public boolean nextBoolean(String msg) {
        this.output.println(msg);
        return this.nextBoolean();
    }
    public boolean nextBoolean(String msg, boolean condizione, String errore) {
        this.output.println(msg);
        boolean v = this.nextBoolean();
        while(condizione != v) {
            System.out.println(errore);
            v = this.nextBoolean();
        }

        return v;
    }

    public Date nextDate(String msg, Predicate<Date> condizione, String errore) {
        Date data;
        int giorno;
        int mese;
        int anno;

        System.out.println(msg);
        while(true) {
            try {
                String l = this.nextLine();
                String[] vs = l.split("/");
                if (vs.length != 3) {
                    System.out.println("Data invalida");
                    continue;
                }

                giorno = Integer.parseInt(vs[0]);
                mese = Integer.parseInt(vs[1]);
                anno = Integer.parseInt(vs[2]);

                if (giorno < 1 || giorno > 31 || mese < 1 || mese > 12) {
                    System.out.println("Data invalida");
                    continue;
                }
            } catch(Exception e) {
                System.out.println("Data invalida");
                continue;
            }

            data = new Date(anno -1900, mese - 1, giorno, 0, 0);

            if (!condizione.test(data)) {
                System.out.println(errore);
                continue;
            }

            break;
        }

        return data;
    }
    
    public float nextFloat() {
        float v;

        while(true) {
            try {
                v = this.scanner.nextFloat();
            } catch(InputMismatchException e) {
                this.output.println("Input invalido");
                this.scanner.nextLine();
                continue;
            }
            this.scanner.nextLine(); // Elimina \n lasciato da nextFloat
            break;
        }

        return v;
    }
    public float nextFloat(String msg) {
        this.output.println(msg);
        return this.nextFloat();
    }
    public float nextFloat(String msg, DoublePredicate condizione, String errore) {
        this.output.println(msg);
        int v = this.nextInt();
        while(!condizione.test(v)) {
            System.out.println(errore);
            v = this.nextInt();
        }

        return v;
    }
}