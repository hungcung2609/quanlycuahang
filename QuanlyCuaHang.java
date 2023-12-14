package QuanlyCHhoa;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
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
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class QuanlyCuaHang {
    private static JFrame frame;
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
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            loadCustomFont("C:\\Users\\Admin\\Downloads\\[cnttqn.net] UTM Beautiful Caps.ttf");
            frame = new JFrame("QUẢN LÝ CỦA HÀNG HOA");
            JLabel Anh = new JLabel();
            JTextArea Vanban =  new JTextArea(20,80);
            Vanban.setBackground(Color.RED);
            Vanban.setForeground(Color.WHITE);
            JScrollPane scrollPane = new JScrollPane(Vanban);

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
                        String VanbanGth = "E:\\code\\Java\\QuanlyCHhoa\\gioithieu.txt";
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
                JMenuItem HoaCatCanh = new JMenuItem("Hoa cắt cành");
                JMenuItem PhanBon = new JMenuItem("Phân Bón");
                JMenuItem Latrangtri = new JMenuItem("Lá Trang Trí");
                JMenuItem HoaNhapKhau = new JMenuItem("Hoa Nhập Khẩu");
                Sanpham.add(HoaCatCanh);
                Sanpham.add(PhanBon);
                Sanpham.add(Latrangtri);
                Sanpham.add(HoaNhapKhau);
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
            Font customFont = new Font("MyFont",Font.BOLD,40);
            Tieude.setFont(customFont);
            Tieude.setForeground(Color.BLUE);
            frame.add(Tieude);

            
            frame.add(Anh);
            frame.addComponentListener((new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e){
                    int ChieuRongCuaSO = frame.getContentPane().getWidth();
                    int ChieuCaoCuaSO = frame.getContentPane().getHeight();
                    String imagePath = "E:\\code\\Java\\QuanlyCHhoa\\hoa.jpg";
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
            

            frame.setSize(1300,700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());
            frame.setVisible(true);
        });
    }
}