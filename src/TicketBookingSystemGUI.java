import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Event {
    private String name;
    private String tanggal;
    private String tempat;
    private int kuotaTiket;
    private static String currentUserRole;
    private static String currentUsername;


    public Event(String name, String tanggal, String tempat, int kuotaTiket) {
        this.name = name;
        this.tanggal = tanggal;
        this.tempat = tempat;
        this.kuotaTiket = kuotaTiket;
    }

    public String getName() {
        return name;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getTempat() {
        return tempat;
    }

    public int getKuotaTiket() {
        return kuotaTiket;
    }

    public void setKuotaTiket(int kuotaTiket) {
        this.kuotaTiket = kuotaTiket;
    }

    public void setName(String newEventName) {
    }

    public void setTanggal(String newTanggal) {
    }

    public void setTempat(String newTempat) {
    }
    private static String pengunjungUsername;
    private static String adminUsername;
    private static boolean isLoggedInPengunjung = false;
    private static boolean isLoggedInAdmin = false;

}

class Ticket {
    private static int ticketIdCounter = 1;
    private int id;
    private String eventName;
    private int quantity;

    public Ticket(String eventName, int quantity) {
        this.id = ticketIdCounter++;
        this.eventName = eventName;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public int getQuantity() {
        return quantity;
    }
}


public class TicketBookingSystemGUI {
    private static List<Event> events = new ArrayList<>();
    private static List<Ticket> bookedTickets = new ArrayList<>();
    private static String pengunjungUsername;
    private static String adminUsername;
    private static boolean isLoggedInPengunjung = false;
    private static boolean isLoggedInAdmin = false;


    public static void main(String[] args) {
        // Menambahkan data event
        events.add(new Event("Konser Musik", "10 Oktober 2023", "Gedung Konser", 100));
        events.add(new Event("Pameran Seni", "15 Oktober 2023", "Galeri Seni", 50));
        events.add(new Event("Teater Komedi", "20 Oktober 2023", "Teater Kecil", 80));
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void loginPengunjung() {
        String username = JOptionPane.showInputDialog("Masukkan username pengunjung:");
        if (isValidPengunjung(username)) {
            pengunjungUsername = username;
            isLoggedInPengunjung = true;
            JOptionPane.showMessageDialog(null, "Login berhasil sebagai pengunjung.");
            createVisitorPanel();
        } else {
            JOptionPane.showMessageDialog(null, "Login gagal. Username tidak valid.");
        }
    }

    private static void buatAkunPengunjung() {
        String username = JOptionPane.showInputDialog("Masukkan username pengunjung baru:");
        if (!isValidPengunjung(username)) {
            pengunjungUsername = username;
            isLoggedInPengunjung = true;
            JOptionPane.showMessageDialog(null, "Akun pengunjung berhasil dibuat dan login.");
            createVisitorPanel();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal membuat akun. Username sudah digunakan.");
        }
    }

    private static void loginAdmin() {
        String username = JOptionPane.showInputDialog("Masukkan username admin:");
        if (isValidAdmin(username)) {
            adminUsername = username;
            isLoggedInAdmin = true;
            JOptionPane.showMessageDialog(null, "Login berhasil sebagai admin.");
            createAdminPanel();
        } else {
            JOptionPane.showMessageDialog(null, "Login gagal. Username tidak valid.");
        }
    }

    private static void buatAkunAdmin() {
        String username = JOptionPane.showInputDialog("Masukkan username admin baru:");
        if (!isValidAdmin(username)) {
            adminUsername = username;
            isLoggedInAdmin = true;
            JOptionPane.showMessageDialog(null, "Akun admin berhasil dibuat dan login.");
            createAdminPanel();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal membuat akun. Username sudah digunakan.");
        }
    }

    private static boolean isValidPengunjung(String username) {
        return username != null && !username.isEmpty();
    }

    private static boolean isValidAdmin(String username) {
        return username != null && !username.isEmpty();
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Sistem Pemesanan Tiket");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 650);
        frame.getContentPane().setBackground(new Color(0, 74, 173)); // Warna biru
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        createWelcomePanel(frame.getContentPane());

        frame.add(Box.createRigidArea(new Dimension(0, 100))); // Margin 10 di bawah frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createWelcomePanel(Container container) {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(new Color(0, 74, 173)); // Warna biru
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));

        // Path file logo
        String logoPath = "C:/Users/ym/Downloads/TixEase.png";

        ImageIcon originalIcon = new ImageIcon(logoPath);
        Image originalImage = originalIcon.getImage();

        // Menyesuaikan ukuran logo
        int newWidth = 400;
        int newHeight = 300;

        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea welcomeText = new JTextArea(
                "Selamat datang di TixEase, platform pemesanan tiket yang mudah! " +
                        "Temukan berbagai event menarik disini. " +
                        "Pilih peran Anda untuk memulai."
        );
        welcomeText.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setBackground(new Color(0, 74, 173));
        welcomeText.setWrapStyleWord(true);
        welcomeText.setLineWrap(true);
        welcomeText.setEditable(false);
        welcomeText.setBorder(BorderFactory.createEmptyBorder(0, 140, 15, 140));
        welcomeText.setAlignmentX(JTextArea.CENTER_ALIGNMENT); // Perubahan ini
        welcomeText.setAlignmentY(JTextArea.CENTER_ALIGNMENT); // Juga perlu ditambahkan

        JScrollPane scrollPane = new JScrollPane(welcomeText);
        scrollPane.setAlignmentX(JTextArea.CENTER_ALIGNMENT);


        JButton userButton = createStyledButton("Pengunjung");
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPengunjungAndShowVisitorPanel(container);
            }
        });

        JButton adminButton = createStyledButton("Admin");
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAdminAndShowAdminPanel(container);
            }
        });

        welcomePanel.add(Box.createVerticalGlue());
        welcomePanel.add(createCenteredPanel(logoLabel));
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        welcomePanel.add(welcomeText);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(createCenteredPanel(userButton));
        welcomePanel.add(Box.createVerticalStrut(5)); // Mengganti RigidArea dengan Strut
        welcomePanel.add(createCenteredPanel(adminButton));
        welcomePanel.add(Box.createVerticalGlue());

        container.add(welcomePanel);
    }

    private static void createAdminPanel(Container container) {
    }

    private static void loginPengunjungAndShowVisitorPanel(Container container) {
        JPanel loginPengunjungPanel = new JPanel();
        loginPengunjungPanel.setBackground(new Color(0, 74, 173)); // Warna biru
        loginPengunjungPanel.setLayout(new BoxLayout(loginPengunjungPanel, BoxLayout.Y_AXIS));

        // Path file logo
        String logoPath = "C:/Users/ym/Downloads/TixEase.png";
        ImageIcon originalIcon = new ImageIcon(logoPath);
        Image originalImage = originalIcon.getImage();
        // Menyesuaikan ukuran logo
        int newWidth = 400;
        int newHeight = 300;

        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPengunjungPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton loginPengunjungButton = createStyledButton("Login Pengunjung");
        loginPengunjungButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPengunjung();
                if (isLoggedInPengunjung) {
                    createVisitorPanel(container);
                } else {
                    createWelcomePanel(container); // Tambahkan ini untuk kembali ke menu utama
                }
            }
        });

        JButton buatAkunPengunjungButton = createStyledButton("Buat Akun Pengunjung");
        buatAkunPengunjungButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buatAkunPengunjung();
                if (isLoggedInPengunjung) {
                    createVisitorPanel(container);
                } else {
                    createWelcomePanel(container); // Tambahkan ini untuk kembali ke menu utama
                }
            }
        });

        loginPengunjungPanel.add(createCenteredPanel(logoLabel));
        // Panel atas dengan warna biru
        JPanel topMarginPanel = new JPanel();
        topMarginPanel.setBackground(new Color(0, 74, 173));
        topMarginPanel.setPreferredSize(new Dimension(1, 25));
        loginPengunjungPanel.add(topMarginPanel);
        loginPengunjungPanel.add(createCenteredPanel(loginPengunjungButton));
        loginPengunjungPanel.add(createCenteredPanel(buatAkunPengunjungButton));

        JPanel bottomMarginPanel = new JPanel();
        bottomMarginPanel.setBackground(new Color(0, 74, 173));
        bottomMarginPanel.setPreferredSize(new Dimension(1, 150));
        loginPengunjungPanel.add(bottomMarginPanel);

        container.removeAll();
        container.add(loginPengunjungPanel);
        container.revalidate();
        container.repaint();
    }
    private static void loginAdminAndShowAdminPanel(Container container) {
        JPanel loginAdminPanel = new JPanel();
        loginAdminPanel.setBackground(new Color(0, 74, 173)); // Warna biru
        loginAdminPanel.setLayout(new BoxLayout(loginAdminPanel, BoxLayout.Y_AXIS));

        // Path file logo
        String logoPath = "C:/Users/ym/Downloads/TixEase.png";
        ImageIcon originalIcon = new ImageIcon(logoPath);
        Image originalImage = originalIcon.getImage();
        // Menyesuaikan ukuran logo
        int newWidth = 400;
        int newHeight = 300;

        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginAdminPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton loginAdminButton = createStyledButton("Login Admin");
        loginAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAdmin();
                if (isLoggedInAdmin){
                    createAdminPanel(container);
                } else {
                    createWelcomePanel(container); // Tambahkan ini untuk kembali ke menu utama
                }
            }
        });

        JButton buatAkunAdminButton = createStyledButton("Buat Akun Admin");
        buatAkunAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buatAkunAdmin();
                if (isLoggedInAdmin) {
                    createAdminPanel(container);
                } else {
                    createWelcomePanel(container); // Tambahkan ini untuk kembali ke menu utama
                }
            }
        });

        loginAdminPanel.add(createCenteredPanel(logoLabel));
        // Panel atas dengan warna biru
        JPanel topMarginPanel = new JPanel();
        topMarginPanel.setBackground(new Color(0, 74, 173));
        topMarginPanel.setPreferredSize(new Dimension(1, 25));
        loginAdminPanel.add(topMarginPanel);
        loginAdminPanel.add(createCenteredPanel(loginAdminButton));
        loginAdminPanel.add(createCenteredPanel(buatAkunAdminButton));

        JPanel bottomMarginPanel = new JPanel();
        bottomMarginPanel.setBackground(new Color(0, 74, 173));
        bottomMarginPanel.setPreferredSize(new Dimension(1, 150));
        loginAdminPanel.add(bottomMarginPanel);

        container.removeAll();
        container.add(loginAdminPanel);
        container.revalidate();
        container.repaint();
    }
    private static void createVisitorPanel(Container container) {
    }


    private static void createAdminPanel() {
        JFrame adminFrame = new JFrame("Panel Admin");
        adminFrame.setSize(800, 650);
        adminFrame.getContentPane().setBackground(new Color(0, 74, 173));
        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.Y_AXIS));

        // Path file logo
        String logoPath = "C:/Users/ym/Downloads/TixEase1.png";
        ImageIcon originalIcon = new ImageIcon(logoPath);
        Image originalImage = originalIcon.getImage();
        // Menyesuaikan ukuran logo
        int newWidth = 200;
        int newHeight = 100;

        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminFrame.add(Box.createRigidArea(new Dimension(0, 100))); // Margin 10 di bawah frame

        JButton viewEventsButton = createStyledButton("Lihat Daftar Event");
        viewEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEventList();
            }
        });

        JButton addEventButton = createStyledButton("Tambah Event");
        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEvent();
            }
        });

        JButton editEventButton = createStyledButton("Edit Event");
        editEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editEvent();
            }
        });

        JButton deleteEventButton = createStyledButton("Hapus Event");
        deleteEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEvent();
            }
        });

        adminPanel.add(logoLabel); // Menambah logo ke panel admin
        // Panel atas dengan warna biru
        JPanel topMarginPanel = new JPanel();
        topMarginPanel.setBackground(new Color(0, 74, 173));
        topMarginPanel.setPreferredSize(new Dimension(1, 130));
        adminPanel.add(topMarginPanel);
        adminPanel.add(createCenteredPanel(viewEventsButton));
        adminPanel.add(createCenteredPanel(addEventButton));
        adminPanel.add(createCenteredPanel(editEventButton));

        adminPanel.add(createCenteredPanel(deleteEventButton));
        JPanel bottomMarginPanel = new JPanel();
        bottomMarginPanel.setBackground(new Color(0, 74, 173));
        bottomMarginPanel.setPreferredSize(new Dimension(1, 150));
        adminPanel.add(bottomMarginPanel);
        adminFrame.add(adminPanel);

        adminFrame.setLocationRelativeTo(null);
        adminFrame.setVisible(true);
    }

    private static void createVisitorPanel() {
        if (isLoggedInPengunjung) {
            JFrame visitorFrame = new JFrame("Panel Pengunjung");
            visitorFrame.setSize(800, 650);
            visitorFrame.getContentPane().setBackground(new Color(0, 74, 173));

            JPanel visitorPanel = new JPanel();
            visitorPanel.setLayout(new BoxLayout(visitorPanel, BoxLayout.Y_AXIS));

            // Path file logo
            String logoPath = "C:/Users/ym/Downloads/TixEase1.png";
            ImageIcon originalIcon = new ImageIcon(logoPath);
            Image originalImage = originalIcon.getImage();
            // Menyesuaikan ukuran logo
            int newWidth = 200;
            int newHeight = 100;

            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            JLabel logoLabel = new JLabel(resizedIcon);
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            visitorFrame.add(Box.createRigidArea(new Dimension(0, 100))); // Margin 10 di bawah frame


            JButton bookTicketButton = createStyledButton("Pesan Tiket");
            bookTicketButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    bookTicket();
                }
            });

            JButton cancelTicketButton = createStyledButton("Batal Tiket");
            cancelTicketButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cancelTicket();
                }
            });

            JButton viewEventsButton = createStyledButton("Lihat Daftar Event");
            viewEventsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showEventList();
                }
            });

            visitorPanel.add(logoLabel); // Menambah logo ke panel admin
            // Panel atas dengan warna biru
            JPanel topMarginPanel = new JPanel();
            topMarginPanel.setBackground(new Color(0, 74, 173));
            topMarginPanel.setPreferredSize(new Dimension(1, 150));
            visitorPanel.add(topMarginPanel);

            visitorPanel.add(createCenteredPanel(bookTicketButton));
            visitorPanel.add(createCenteredPanel(cancelTicketButton));
            visitorPanel.add(createCenteredPanel(viewEventsButton));

            JPanel bottomMarginPanel = new JPanel();
            bottomMarginPanel.setBackground(new Color(0, 74, 173));
            bottomMarginPanel.setPreferredSize(new Dimension(1, 200));
            visitorPanel.add(bottomMarginPanel);


            visitorFrame.add(visitorPanel);

            visitorFrame.setLocationRelativeTo(null);
            visitorFrame.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Anda harus login sebagai pengunjung.");
        }
    }

    private static JPanel createCenteredPanel(Component content) {
        JPanel centeredPanel = new JPanel();
        centeredPanel.setLayout(new GridBagLayout());
        centeredPanel.setBackground(new Color(0, 74, 173)); // Warna biru

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        centeredPanel.add(content, gbc);

        return centeredPanel;
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(500, 50));
        button.setBackground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        return button;
    }


    private static void addEvent() {
        String eventName = JOptionPane.showInputDialog("Masukkan nama event:");
        String tanggal = JOptionPane.showInputDialog("Masukkan tanggal pelaksanaan:");
        String tempat = JOptionPane.showInputDialog("Masukkan tempat pelaksanaan:");
        int kuotaTiket = Integer.parseInt(JOptionPane.showInputDialog("Masukkan kuota tiket:"));

        if (findEvent(eventName) == null) {
            addEvent(new Event(eventName, tanggal, tempat, kuotaTiket));
            JOptionPane.showMessageDialog(null, "Event " + eventName + " berhasil ditambahkan.");
        } else {
            JOptionPane.showMessageDialog(null, "Event dengan nama " + eventName + " sudah ada.");
        }
    }

    private static void editEvent() {
        String eventNameToEdit = JOptionPane.showInputDialog("Masukkan nama event yang akan diedit:\n" + getEventNames());

        if (eventNameToEdit != null) {
            Event event = findEvent(eventNameToEdit);
            if (event != null) {
                String newEventName = JOptionPane.showInputDialog("Masukkan nama baru untuk event " + eventNameToEdit + ":");
                if (newEventName != null) {
                    String newTanggal = JOptionPane.showInputDialog("Masukkan tanggal baru untuk event " + eventNameToEdit + ":");
                    String newTempat = JOptionPane.showInputDialog("Masukkan tempat baru untuk event " + eventNameToEdit + ":");
                    int newKuotaTiket = Integer.parseInt(JOptionPane.showInputDialog("Masukkan kuota tiket baru untuk event " + eventNameToEdit + ":"));

                    event.setName(newEventName);
                    event.setTanggal(newTanggal);
                    event.setTempat(newTempat);
                    event.setKuotaTiket(newKuotaTiket);

                    JOptionPane.showMessageDialog(null, "Event " + eventNameToEdit + " berhasil diedit menjadi " + newEventName + ".");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Event dengan nama " + eventNameToEdit + " tidak ditemukan.");
            }
        }
    }

    private static void deleteEvent() {
        String eventNameToDelete = JOptionPane.showInputDialog("Masukkan nama event yang akan dihapus:\n" + getEventNames());

        Event event = findEvent(eventNameToDelete);
        if (event != null) {
            deleteEvent(event);
            JOptionPane.showMessageDialog(null, "Event " + eventNameToDelete + " berhasil dihapus.");
        } else {
            JOptionPane.showMessageDialog(null, "Event dengan nama " + eventNameToDelete + " tidak ditemukan.");
        }
    }

    private static void bookTicket() {
        String[] eventNames = getEventNamesArray();
        if (eventNames.length > 0) {
            String selectedEvent = (String) JOptionPane.showInputDialog(null, "Pilih event untuk memesan tiket:",
                    "Pilih Event", JOptionPane.PLAIN_MESSAGE, null, eventNames, eventNames[0]);

            if (selectedEvent != null) {
                int availableTickets = getAvailableTickets(selectedEvent);
                int quantity = Integer.parseInt(JOptionPane.showInputDialog("Masukkan jumlah tiket (sisa tiket: " + availableTickets + "):"));

                Event event = findEvent(selectedEvent);
                if (event != null) {
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(null, "Jumlah tiket harus lebih dari 0.");
                    } else if (quantity <= availableTickets) {
                        bookTicket(new Ticket(selectedEvent, quantity));
                        JOptionPane.showMessageDialog(null, "Tiket berhasil dipesan untuk event " + selectedEvent + " sebanyak " + quantity + " tiket.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, tiket tidak mencukupi.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Event dengan nama " + selectedEvent + " tidak ditemukan.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tidak ada event yang tersedia untuk dipesan.");
        }
    }

    private static void cancelTicket() {
        String[] eventNames = getEventNamesArray();
        if (eventNames.length > 0) {
            String selectedEvent = (String) JOptionPane.showInputDialog(null, "Pilih event untuk membatalkan tiket:",
                    "Pilih Event", JOptionPane.PLAIN_MESSAGE, null, eventNames, eventNames[0]);

            if (selectedEvent != null) {
                List<Ticket> userTickets = getUserTickets(selectedEvent);

                if (!userTickets.isEmpty()) {
                    String[] ticketIds = userTickets.stream().map(ticket -> String.valueOf(ticket.getId())).toArray(String[]::new);
                    String selectedTicketId = (String) JOptionPane.showInputDialog(null, "Pilih tiket untuk dibatalkan:",
                            "Pilih Tiket", JOptionPane.PLAIN_MESSAGE, null, ticketIds, ticketIds[0]);

                    if (selectedTicketId != null) {
                        int ticketId = Integer.parseInt(selectedTicketId);
                        Ticket ticket = findTicket(ticketId);

                        if (ticket != null) {
                            cancelTicket(ticket);
                            JOptionPane.showMessageDialog(null, "Tiket dengan ID " + ticketId + " berhasil dibatalkan.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Tiket dengan ID " + ticketId + " tidak ditemukan.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Anda tidak memiliki tiket untuk event ini.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tidak ada event yang tersedia untuk membatalkan tiket.");
        }
    }

    private static List<Ticket> getUserTickets(String eventName) {
        List<Ticket> userTickets = new ArrayList<>();
        for (Ticket ticket : bookedTickets) {
            if (ticket.getEventName().equals(eventName)) {
                userTickets.add(ticket);
            }
        }
        return userTickets;
    }


    private static void showEventList() {
        StringBuilder eventList = new StringBuilder("Daftar event:\n");
        for (Event event : events) {
            eventList.append("Nama: ").append(event.getName())
                    .append(", Tanggal: ").append(event.getTanggal())
                    .append(", Tempat: ").append(event.getTempat())
                    .append(", Kuota Tiket: ").append(event.getKuotaTiket())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, eventList.toString());
    }

    private static Event findEvent(String eventName) {
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                return event;
            }
        }
        return null;
    }

    private static String[] getEventNamesArray() {
        String[] eventNames = new String[events.size()];
        for (int i = 0; i < events.size(); i++) {
            eventNames[i] = events.get(i).getName();
        }
        return eventNames;
    }

    private static int getAvailableTickets(String eventName) {
        int totalTickets = 0;
        for (Ticket ticket : bookedTickets) {
            if (ticket.getEventName().equals(eventName)) {
                totalTickets += ticket.getQuantity();
            }
        }
        Event event = findEvent(eventName);
        return event != null ? event.getKuotaTiket() - totalTickets : 0;
    }

    private static void bookTicket(Ticket ticket) {
        bookedTickets.add(ticket);
    }

    private static Ticket findTicket(int ticketId) {
        for (Ticket ticket : bookedTickets) {
            if (ticket.getId() == ticketId) {
                return ticket;
            }
        }
        return null;
    }

    private static void cancelTicket(Ticket ticket) {
        bookedTickets.remove(ticket);
    }

    private static void addEvent(Event event) {
        events.add(event);
    }

    private static void deleteEvent(Event event) {
        events.remove(event);
    }

    private static String getEventNames() {
        if (events.isEmpty()) {
            return "Tidak ada event yang tersedia.";
        }

        StringBuilder eventNames = new StringBuilder("Event yang sudah ada:\n");
        for (Event event : events) {
            eventNames.append(event.getName()).append("\n");
        }
        return eventNames.toString();
    }
}

