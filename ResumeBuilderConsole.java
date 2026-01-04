import java.util.*;

public class ResumeBuilderConsole {

    static class Resume {
        String name;
        String email;
        String phone;
        String college;
        String qualification;
        String yearOfPassing;
        String cgpa;
        List<String> skills = new ArrayList<>();
        List<String> projects = new ArrayList<>();
        List<String> experience = new ArrayList<>();
        String summary;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Resume res = new Resume();

        System.out.print("Enter your full name: ");
        res.name = sc.nextLine();

        // Validate email
        while (true) {
            System.out.print("Enter your email (must end with @gmail.com): ");
            String email = sc.nextLine().trim();
            if (isValidGmail(email)) {
                res.email = email;
                break;
            } else {
                System.out.println("Invalid email! It must end with “@gmail.com”. Please try again.");
            }
        }

        // Validate phone number
        while (true) {
            System.out.print("Enter your phone number (10 digits): ");
            String phone = sc.nextLine().trim();
            if (isValidPhone(phone)) {
                res.phone = phone;
                break;
            } else {
                System.out.println("Invalid phone number! It must be exactly 10 digits (only numbers). Please try again.");
            }
        }

        System.out.print("Enter your college / university name: ");
        res.college = sc.nextLine();

        System.out.print("Enter your highest qualification: ");
        res.qualification = sc.nextLine();

        System.out.print("Enter your year of passing: ");
        res.yearOfPassing = sc.nextLine();

        System.out.print("Enter your CGPA: ");
        res.cgpa = sc.nextLine();

        System.out.print("Enter a brief professional summary (or objective): ");
        res.summary = sc.nextLine();

        System.out.println("Enter your skills (comma separated): ");
        String skillsLine = sc.nextLine();
        String[] skillArr = skillsLine.split(",");
        for (String s : skillArr) {
            res.skills.add(s.trim());
        }

        System.out.print("How many projects you want to add? ");
        int projCount = 0;
        try {
            projCount = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            projCount = 0;
        }
        for (int i = 0; i < projCount; i++) {
            System.out.print(" Project " + (i + 1) + " title/details: ");
            String p = sc.nextLine();
            res.projects.add(p);
        }

        System.out.print("How many work experiences you want to add? ");
        int expCount = 0;
        try {
            expCount = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            expCount = 0;
        }
        for (int i = 0; i < expCount; i++) {
            System.out.print(" Experience " + (i + 1) + " description: ");
            String e = sc.nextLine();
            res.experience.add(e);
        }

        System.out.println("\n\n====== GENERATED RESUME ======\n");
        printCenteredResume(res, 80);  // assuming width = 80 chars

        sc.close();
    }

    private static boolean isValidGmail(String email) {
        if (email == null) return false;
        email = email.toLowerCase();
        return email.endsWith("@gmail.com") && email.indexOf('@') < email.lastIndexOf("gmail.com");
        // This check is simple: endsWith, and ensures there's something before the '@'
    }

    private static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        if (phone.length() != 10) return false;
        for (char c : phone.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static void printCenteredResume(Resume r, int width) {
        final String LINE_SEP = repeatChar('-', width);
        printCentered(LINE_SEP, width);
        printCentered("NAME: " + r.name, width);
        printCentered("Email: " + r.email, width);
        printCentered("Phone: " + r.phone, width);
        printBlankLine(width);

        printCentered("SUMMARY:", width);
        wrapAndPrintCentered(r.summary, width);
        printBlankLine(width);

        printCentered("EDUCATION:", width);
        printCentered("College: " + r.college, width);
        printCentered("Qualification: " + r.qualification, width);
        printCentered("Year of Passing: " + r.yearOfPassing, width);
        printCentered("CGPA: " + r.cgpa, width);
        printBlankLine(width);

        if (!r.skills.isEmpty()) {
            printCentered("SKILLS:", width);
            for (String s : r.skills) {
                printCentered("• " + s, width);
            }
            printBlankLine(width);
        }

        if (!r.projects.isEmpty()) {
            printCentered("PROJECTS:", width);
            for (String p : r.projects) {
                wrapAndPrintCentered("• " + p, width);
            }
            printBlankLine(width);
        }

        if (!r.experience.isEmpty()) {
            printCentered("EXPERIENCE:", width);
            for (String e : r.experience) {
                wrapAndPrintCentered("• " + e, width);
            }
            printBlankLine(width);
        }

        printCentered(LINE_SEP, width);
    }

    private static void printBlankLine(int width) {
        System.out.println();
    }

    private static void printCentered(String line, int width) {
        if (line == null) {
            System.out.println();
            return;
        }
        int len = line.length();
        if (len >= width) {
            System.out.println(line);
        } else {
            int padding = (width - len) / 2;
            String pad = repeatChar(' ', padding);
            System.out.println(pad + line);
        }
    }

    private static void wrapAndPrintCentered(String text, int width) {
        if (text == null) {
            System.out.println();
            return;
        }
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (sb.length() + 1 + w.length() <= width) {
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(w);
            } else {
                printCentered(sb.toString(), width);
                sb = new StringBuilder(w);
            }
        }
        if (sb.length() > 0) {
            printCentered(sb.toString(), width);
        }
    }

    private static String repeatChar(char ch, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }
}

