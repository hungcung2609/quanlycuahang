package QuanlyCHhoa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class QuanlyCuaHang {
    private static JFrame frame;
    private static JPanel contentPanel;
    private static JDialog dialogHoaHong;
    private static JDialog dialogHoaTulip;
    private static JDialog dialogLatrangtri;
    private static JDialog dialogHoaCamtuCau;
    
    private static JFrame loginFrame;
    private static JTextField usernameField;
    private static JPasswordField passwordField;

    private static void loadCustomFont(String fontPath){
        try{
            File fontFile = new File(fontPath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] agrs){
        SwingUtilities.invokeLater(() -> {
            GiaodienDangnhap();
        });
    }
    private static void GiaodienDangnhap() {
        loginFrame = new JFrame("Đăng Nhập");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400,200);
        loginFrame.setLayout(new BorderLayout());
        loginFrame.setLocationRelativeTo(null);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3,2));

        JLabel usernameLabel = new JLabel("Tên đăng nhập: ");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Mật khẩu: ");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Đăng Nhập");

        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                boolean isAuthenticated = authenticate(username, password);

                if (isAuthenticated) {
                    loginFrame.dispose();
                    JOptionPane.showMessageDialog(loginFrame, "Welcome","Login Success", JOptionPane.INFORMATION_MESSAGE);

                    Openmain(username);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // Empty label for spacing
        loginPanel.add(loginButton);

        loginFrame.add(loginPanel, BorderLayout.CENTER);
        loginFrame.setVisible(true);
    }

    private static boolean authenticate(String username, String password) {
        // Perform authentication logic here
        // For simplicity, let's consider any non-empty username and password as valid
        return !username.isEmpty() && !password.isEmpty();
    }

    public static void Openmain(String username) {
        SwingUtilities.invokeLater(() -> {
            loadCustomFont("Cosmopolitan Script Medium.ttf");
            frame = new JFrame("QUẢN LÝ CỦA HÀNG HOA");
            frame.setLocationRelativeTo(null);
            JLabel Anh = new JLabel();
            JTextArea Vanban =  new JTextArea(20,800);
            Vanban.setBackground(Color.RED);
            Vanban.setForeground(Color.WHITE);

            JMenuBar menuBar1 = new JMenuBar();
            Dimension menuBar1size = menuBar1.getPreferredSize();
            menuBar1size = new Dimension(menuBar1size.width, 60);
            menuBar1.setPreferredSize(menuBar1size);
            menuBar1.setBackground(Color.PINK);
            JMenu Trangchu = new JMenu("Trang chủ");
            Trangchu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e){
                    Trangchu.setForeground(Color.BLUE);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    Trangchu.setForeground(UIManager.getColor("Menu.foreground"));
                }
            });
            JMenu Gioithieu = new JMenu("Giới Thiệu");
                JMenuItem Thongtin = new JMenuItem("Thông Tin Cửa Hàng");
                JMenuItem Thuvien = new JMenuItem("Thư Viện");
                Gioithieu.add(Thongtin);
                Gioithieu.add(Thuvien);
                Thongtin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose(); //tam thoi tat cua so hien tai

                    JDialog dialog = new JDialog(null, "Thông tin", Dialog.ModalityType.APPLICATION_MODAL);
                    dialog.setSize(frame.getContentPane().getSize());
                    dialog.setLocationRelativeTo(null);
                    dialog.getContentPane().setBackground(Color.RED);
                    try{
                        String VanbanGth = "Gth.txt";
                        BufferedReader Doc = new BufferedReader(new FileReader(VanbanGth));
                        StringBuilder content = new StringBuilder();
                        String line;
                        while ((line= Doc.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        Doc.close();
                        Vanban.setText(content.toString());
                    }catch (IOException ep) {
                        ep.printStackTrace();
                    }
                    dialog.revalidate();
                    dialog.repaint();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent windowEvent){
                            frame.setVisible(true);
                        }
                    });
                    dialog.setVisible(true);
                }
            });
            Gioithieu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e){
                    Gioithieu.setForeground(Color.BLUE);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    Gioithieu.setForeground(UIManager.getColor("Menu.foreground"));
                }
            });
               
            JMenu Sanpham = new JMenu("Sản Phẩm");
                JMenuItem HoaHong = new JMenuItem("Hoa Hồng");
                HoaHong.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CuasoHoaHong();
                }});
                
                JMenuItem HoaTulip = new JMenuItem("Hoa Tu Lip");
                HoaTulip.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CuasoHoaTulip();
                }});

                JMenuItem Latrangtri = new JMenuItem("Lá Trang Trí");
                Latrangtri.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CuasoLatrangtri();
                }});

                JMenuItem HoaCamtuCau = new JMenuItem("Hoa Cẩm Tú Cầu");
                HoaCamtuCau.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CuasoHoaCamtuCau();
                }});

                Sanpham.add(HoaHong);
                Sanpham.add(HoaTulip);
                Sanpham.add(Latrangtri);
                Sanpham.add(HoaCamtuCau);
            Sanpham.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e){
                    Sanpham.setForeground(Color.BLUE);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    Sanpham.setForeground(UIManager.getColor("Menu.foreground"));
                }
            });
            JMenu HethongCH = new JMenu("Hệ Thống Cửa Hàng");
                JMenuItem TruSoChinh = new JMenuItem("Tru Sở Chính");
                JMenuItem Diachi = new JMenuItem("Địa Chỉ");
                HethongCH.add(TruSoChinh);
                HethongCH.add(Diachi);
            HethongCH.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e){
                    HethongCH.setForeground(Color.BLUE);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    HethongCH.setForeground(UIManager.getColor("Menu.foreground"));
                }
            });    
            JMenu LienHe = new JMenu("Liên Hệ");
                JMenuItem Lienhe = new JMenuItem("Liên hệ");
                JMenuItem Cauhoi = new JMenuItem("Các Câu Hỏi Thường Gặp");
                LienHe.add(Lienhe);
                LienHe.add(Cauhoi);
            LienHe.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e){
                    LienHe.setForeground(Color.BLUE);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    LienHe.setForeground(UIManager.getColor("Menu.foreground"));
                }
            });
            menuBar1.add(Trangchu);
            menuBar1.add(Gioithieu);
            menuBar1.add(Sanpham);
            menuBar1.add(HethongCH);
            menuBar1.add(LienHe);
            frame.setJMenuBar(menuBar1);

            JLabel Tieude = new JLabel("THE FLOWER SHOP");
            Font customFont = new Font("customFont",Font.BOLD,40);
            Tieude.setFont(customFont);
            Tieude.setForeground(Color.BLUE);
            frame.add(Tieude);

            frame.add(Anh);
            frame.addComponentListener((new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e){
                    int ChieuRongCuaSO = frame.getContentPane().getWidth();
                    int ChieuCaoCuaSO = frame.getContentPane().getHeight();
                    String imagePath = "hoa.jpg";
                    try{
                        BufferedImage originalImage = ImageIO.read(new File(imagePath));
                        Image Anhresized = originalImage.getScaledInstance(ChieuRongCuaSO, ChieuCaoCuaSO, Image.SCALE_SMOOTH);
                        ImageIcon Iconresized = new ImageIcon(Anhresized);
                        Anh.setIcon(Iconresized);
                        } catch (IOException ex){
                        ex.printStackTrace();
                        }
                }
            }));
            

            frame.setSize(600,500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());
            frame.setVisible(true);
        });
    }

    private static void CuasoHoaHong(){
        System.out.println("Mo cua so Hoa hong");
        frame.dispose(); //tam thoi tat cua so frame
        dialogHoaHong = new JDialog(frame, "Hoa Hồng", Dialog.ModalityType.APPLICATION_MODAL);
        dialogHoaHong.setSize(frame.getContentPane().getSize());
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        dialogHoaHong.setContentPane(contentPanel);
        
        AnhvaTextHoahong();

        dialogHoaHong.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent er){
            resizeImage();
            }
        });
        resizeImage();
        dialogHoaHong.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogHoaHong.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                frame.setVisible(true);
            }
        });
        dialogHoaHong.setLocationRelativeTo(null);
        dialogHoaHong.setVisible(true);
    }

    private static void AnhvaTextHoahong(){
        ImageIcon anh1 = new ImageIcon("hoahongdo.jpg");
        JLabel label1 = new JLabel(anh1);
        contentPanel.add(label1, BorderLayout.WEST);

        ImageIcon anh2 = new ImageIcon("hoahong.jpg");
        JLabel label2 = new JLabel(anh2);
        contentPanel.add(label2, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); 
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton customButton = new JButton("Thông tin");
        buttonPanel.add(customButton);

        customButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(dialogHoaHong, "Đây là mô tả về hoa hồng,....");
        }
    });
    }

    private static void CuasoHoaTulip(){
        System.out.println("Mo cua so Hoa Tulip");
        frame.dispose(); //tam thoi tat cua so frame
        dialogHoaTulip = new JDialog(frame, "Hoa Tulip", Dialog.ModalityType.APPLICATION_MODAL);
        dialogHoaTulip.setSize(670,400);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        dialogHoaTulip.setContentPane(contentPanel);
        
        AnhvaTextHoatulip();

        dialogHoaTulip.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ee){
            
            }
        });
        
        dialogHoaTulip.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogHoaTulip.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                frame.setVisible(true);
            }
        });
        dialogHoaTulip.setLocationRelativeTo(null);
        dialogHoaTulip.setVisible(true);
    }
    private static void AnhvaTextHoatulip(){
        ImageIcon anh1 = new ImageIcon("hoatulip.jpg");
        JLabel label1 = new JLabel(anh1);
        contentPanel.add(label1, BorderLayout.WEST);

        ImageIcon anh2 = new ImageIcon("hoatulip2.jpg");
        JLabel label2 = new JLabel(anh2);
        contentPanel.add(label2, BorderLayout.CENTER);

        ImageIcon anh3 = new ImageIcon("hoahong.jpg");
        JLabel label3 = new JLabel(anh3);
        contentPanel.add(label3, BorderLayout.EAST);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); 
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton customButton = new JButton("Thông tin");
        buttonPanel.add(customButton);

        customButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(dialogHoaHong, "Đây là mô tả về hoa Tulip,....");
        }
    });
    }

    private static void CuasoLatrangtri(){
        System.out.println("Mo cua so Hoa Lá Trang Trí");
        frame.dispose(); //tam thoi tat cua so frame
        dialogLatrangtri = new JDialog(frame, "Lá Trang Trí", Dialog.ModalityType.APPLICATION_MODAL);
        dialogLatrangtri.setSize(frame.getContentPane().getSize());
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        dialogLatrangtri.setContentPane(contentPanel);
        
        AnhvaTextLatrangtri();

        dialogLatrangtri.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ee){
            
            }
        });
        
        dialogLatrangtri.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogLatrangtri.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                frame.setVisible(true);
            }
        });
        dialogLatrangtri.setLocationRelativeTo(null);
        dialogLatrangtri.setVisible(true);
    }
    private static void AnhvaTextLatrangtri(){
        ImageIcon anh1 = new ImageIcon("latrangtri.jpg");
        JLabel label1 = new JLabel(anh1);
        contentPanel.add(label1, BorderLayout.WEST);

        ImageIcon anh2 = new ImageIcon("latrangtri2.jpg");
        JLabel label2 = new JLabel(anh2);
        contentPanel.add(label2, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); 
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton customButton = new JButton("Thông tin");
        buttonPanel.add(customButton);

        customButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(dialogHoaHong, "Đây là mô tả về Lá trang trí,....");
        }
    });
    }


    private static void CuasoHoaCamtuCau(){
        System.out.println("Mo cua so Hoa Cam Tu cau");
        frame.dispose(); //tam thoi tat cua so frame
        dialogHoaCamtuCau = new JDialog(frame, "Hoa Cẩm Tú Cầu", Dialog.ModalityType.APPLICATION_MODAL);
        dialogHoaCamtuCau.setSize(frame.getContentPane().getSize());
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        dialogHoaCamtuCau.setContentPane(contentPanel);
        
        AnhvaTextHoaCamtucau();

        dialogHoaCamtuCau.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ee){
            
            }
        });
        
        dialogHoaCamtuCau.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogHoaCamtuCau.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                frame.setVisible(true);
            }
        });
        dialogHoaCamtuCau.setLocationRelativeTo(null);
        dialogHoaCamtuCau.setVisible(true);
    }
    private static void AnhvaTextHoaCamtucau(){
        ImageIcon anh1 = new ImageIcon("hoacamtucau.jpg");
        JLabel label1 = new JLabel(anh1);
        contentPanel.add(label1, BorderLayout.WEST);

        ImageIcon anh2 = new ImageIcon("hoacamtucau2.jpg");
        JLabel label2 = new JLabel(anh2);
        contentPanel.add(label2, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); 
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton customButton = new JButton("Thông tin");
        buttonPanel.add(customButton);

        customButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(dialogHoaHong, "Đây là mô tả về hoa cẩm tú cầu,....");
        }
    });
    }

    private static void resizeImage() {
        if (contentPanel != null && dialogHoaHong != null && contentPanel.getWidth()>0 && contentPanel.getHeight() >0){            
            Component[] components = contentPanel.getComponents();
            for (Component component : components) {
                if (component instanceof JLabel) {
                    JLabel label = (JLabel) component;
                    Icon icon = label.getIcon();
                    if (icon instanceof ImageIcon){
                        Image image = ((ImageIcon) icon).getImage();
                        Image resizedImage = image.getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_SMOOTH);
                        label.setIcon(new ImageIcon(resizedImage));
                    }
                }
            }
            contentPanel.revalidate();
            contentPanel.repaint();
            
        }
    }
}