package view;

import DAO.orderDao;
import java.awt.Color;
import javax.swing.JButton;
import java.util.ArrayList;

import models.Products;
import models.SpecialBurgers;
import controller.OrderController;
import fastfood.system.CustomerView;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
    
public class OrderingView extends javax.swing.JFrame {
    private OrderController controller; // bat final
    
    Products SPburger1, SPburger2, SPburger3, SPburger4, SPburger5, SPburger6; // Initialize for access
    Products Cburger1, Cburger2, Cburger3, Cburger4, Cburger5;
    Products Burger1, Burger2, Burger3, Burger4, Burger5, Burger6;
    Products Mburger1, Mburger2, Mburger3, Mburger4, Mburger5, Mburger6;
    Products Hotdog1, Hotdog2, Hotdog3, Hotdog4, Hotdog5, Hotdog6;
    Products Grill1, Grill2, Grill3, Grill4, Grill5, Grill6;
    Products Dessert1, Dessert2, Dessert3, Dessert4, Dessert5, Dessert6;
    Products Side1, Side2, Side3, Side4, Side5, Side6;
    Products Drink1, Drink2, Drink3, Drink4, Drink5, Drink6;
    
    public OrderingView() {
        initComponents();
        setLocationRelativeTo(null);
        controller = new OrderController(); // call controller class to access controller methods
        
        // add six of each food sub-category
        controller.addProduct(SPburger1 = new Products("APS", 300));
        controller.addProduct(SPburger2 = new Products("BBO", 320));
        controller.addProduct(SPburger3 = new Products("BBH", 275));
        controller.addProduct(SPburger4 = new Products("DK", 275));
        controller.addProduct(SPburger5 = new Products("PFC", 290));
        controller.addProduct(SPburger6 = new Products("RB", 175));
        
        controller.addProduct(Cburger1 = new Products("TB", 129));
        controller.addProduct(Cburger2 = new Products("SCB", 129));
        controller.addProduct(Cburger3 = new Products("SPC", 129));
        controller.addProduct(Cburger4 = new Products("GCB", 129));
        controller.addProduct(Cburger5 = new Products("RCB", 129));
        
        controller.addProduct(Burger1 = new Products("BEH", 121));
        controller.addProduct(Burger2 = new Products("CHB", 121));
        controller.addProduct(Burger3 = new Products("DBP", 121));
        controller.addProduct(Burger4 = new Products("DTP", 121));
        controller.addProduct(Burger5 = new Products("DCB", 121));
        controller.addProduct(Burger6 = new Products("KPD", 121));
        
        controller.addProduct(Mburger1 = new Products("MVB", 150));
        controller.addProduct(Mburger2 = new Products("MRB", 150));
        controller.addProduct(Mburger3 = new Products("MCO", 150));
        controller.addProduct(Mburger4 = new Products("MBD", 150));
        controller.addProduct(Mburger5 = new Products("MBH", 150));
        controller.addProduct(Mburger6 = new Products("GMB", 150));
        
        controller.addProduct(Hotdog1 = new Products("CHot", 75));
        controller.addProduct(Hotdog2 = new Products("CHung", 75));
        controller.addProduct(Hotdog3 = new Products("CLong", 75));
        controller.addProduct(Hotdog4 = new Products("CBac", 75));
        controller.addProduct(Hotdog5 = new Products("HS", 75));
        controller.addProduct(Hotdog6 = new Products("LHBac", 75));
        
        controller.addProduct(Grill1 = new Products("GS", 229));
        controller.addProduct(Grill2 = new Products("GSS", 229));
        controller.addProduct(Grill3 = new Products("GBP", 229));
        controller.addProduct(Grill4 = new Products("GCL", 229));
        controller.addProduct(Grill5 = new Products("GBD", 229));
        controller.addProduct(Grill6 = new Products("GPB", 229));
        
        controller.addProduct(Dessert1 = new Products("TC", 100));
        controller.addProduct(Dessert2 = new Products("SC", 100));
        controller.addProduct(Dessert3 = new Products("MCT", 100));
        controller.addProduct(Dessert4 = new Products("JRC", 100));
        controller.addProduct(Dessert5 = new Products("COC", 100));
        controller.addProduct(Dessert6 = new Products("CDB", 100));
        
        controller.addProduct(Side1 = new Products("BAC", 75));
        controller.addProduct(Side2 = new Products("BBO", 75));
        controller.addProduct(Side3 = new Products("CMM", 75));
        controller.addProduct(Side4 = new Products("CF", 75));
        controller.addProduct(Side5 = new Products("GSM", 75));
        controller.addProduct(Side6 = new Products("LAS", 75));
        
        controller.addProduct(Drink1 = new Products("UMT", 65));
        controller.addProduct(Drink2 = new Products("TMT", 65));
        controller.addProduct(Drink3 = new Products("SFJ", 65));
        controller.addProduct(Drink4 = new Products("MMT", 65));
        controller.addProduct(Drink5 = new Products("LEM", 65));
        controller.addProduct(Drink6 = new Products("IC", 65));
        
        removeButtonTransparency();
        setDefaultCloseOperation(OrderingView.DISPOSE_ON_CLOSE);
        }
    
    private void incrementProductQuantity(Products product){
        controller.updateQuantity(product, +1);
        refreshOrderTable();
    }
    
    private void decrementProductQuantity(Products product){
        controller.updateQuantity(product, -1);
        refreshOrderTable();
    }

    private void refreshOrderTable() {
        DefaultTableModel table = (DefaultTableModel)jTableMyOrder.getModel();
        table.setRowCount(0);   // refresh list every method call for updated list.   

        for(Products products : controller.getOrderedProducts()){
            table.addRow(new Object[]{products.getQuantity(), products.getName(), products.getPrice(), products.getTotalPrice()});
        }
        table.fireTableDataChanged();  
    }
    
    private void sidebarHighlight(JButton selectedBtn){  // purpose:  highlighting effect when one category is pressed.
        JButton[] hoverbtns = {btnSpBurger, btnHotdogs, btnGrills, btnDesserts, btnSides, btnDrinks, btnBurger, btnMiniBurger, btnChckBurger}; // array  of JButtons
        for(JButton btn : hoverbtns){ // for each JButton btn set BG to white
            btn.setBackground(Color.white);   
            btn.setForeground(Color.black);
        }
        selectedBtn.setBackground(new java.awt.Color(37, 150, 190)); // highlight selected button
        selectedBtn.setForeground(Color.white);
    }
          
    private void removeButtonTransparency(){ // removed: btn opacity and btn increment and decrement highlighting effects when pressed.
        JButton[] btns = {btnInc1, btnDec1, btnInc2, btnDec2, btnInc3, btnDec3, btnInc4, btnDec4, btnInc5, btnDec5, btnInc6, btnDec6, btnInc7, btnInc8,btnInc9, btnInc10,
        btnInc11, btnInc12, btnInc13, btnInc14, btnInc15, btnInc16, btnInc17, btnInc18, btnInc19, btnInc20, btnInc21, btnInc22, btnInc23, btnInc24, btnInc25, btnInc26,
                btnInc27, btnInc28, btnInc29, btnInc30, btnInc31, btnInc32, btnInc33, btnInc34, btnInc35, btnInc36, btnInc37, btnInc38, btnInc39, btnInc40, btnInc41,
                btnInc42, btnInc43, btnInc44, btnInc45, btnInc46, btnInc47, btnInc48, btnInc49, btnInc50, btnInc51, btnInc52, btnInc53, btnDec7, btnDec8, btnDec9,
                btnDec10, btnDec11, btnDec12, btnDec13, btnDec14, btnDec15, btnDec16, btnDec17, btnDec18, btnDec19, btnDec20, btnDec21, btnDec22, btnDec23,
                btnDec24, btnDec25, btnDec26, btnDec27, btnDec28, btnDec29, btnDec30, btnDec31, btnDec32, btnDec33, btnDec34, btnDec35, btnDec36, btnDec37,
                btnDec38, btnDec39, btnDec40, btnDec41, btnDec42, btnDec43, btnDec44, btnDec45, btnDec46, btnDec47, btnDec48, btnDec49, btnDec50, btnDec51, btnDec52, btnDec53, btnOrderingViewExit};
        for(JButton btn : btns){
            btn.setContentAreaFilled(false);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        sidebar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnSpBurger = new javax.swing.JButton();
        btnChckBurger = new javax.swing.JButton();
        btnBurger = new javax.swing.JButton();
        btnMiniBurger = new javax.swing.JButton();
        btnHotdogs = new javax.swing.JButton();
        btnGrills = new javax.swing.JButton();
        btnDesserts = new javax.swing.JButton();
        btnSides = new javax.swing.JButton();
        btnDrinks = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        sidebar3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMyOrder = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnOrderList = new javax.swing.JButton();
        btnPayNow1 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jtxtSubtotal = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jtxtTAX = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jtxtDiscount = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jtxtTotal = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnOrderingViewExit = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel68 = new javax.swing.JPanel();
        jlblSp1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnInc1 = new javax.swing.JButton();
        btnDec1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jtxtQty11 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel69 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnInc2 = new javax.swing.JButton();
        btnDec2 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jtxtQty12 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel71 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnInc3 = new javax.swing.JButton();
        btnDec3 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jtxtQty13 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jPanel70 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnInc4 = new javax.swing.JButton();
        btnDec4 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jtxtQty14 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jPanel72 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnInc5 = new javax.swing.JButton();
        btnDec5 = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        jtxtQty15 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jPanel73 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnInc6 = new javax.swing.JButton();
        btnDec6 = new javax.swing.JButton();
        jTextField11 = new javax.swing.JTextField();
        jtxtQty16 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel75 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        btnInc8 = new javax.swing.JButton();
        btnDec8 = new javax.swing.JButton();
        jTextField10 = new javax.swing.JTextField();
        cb5 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jPanel76 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        btnInc9 = new javax.swing.JButton();
        btnDec9 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        cb4 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jPanel77 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        btnInc10 = new javax.swing.JButton();
        btnDec10 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        cb3 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jPanel78 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        btnInc11 = new javax.swing.JButton();
        btnDec11 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        cb2 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jPanel79 = new javax.swing.JPanel();
        jlblSp2 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        btnInc12 = new javax.swing.JButton();
        btnDec12 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        cb1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel109 = new javax.swing.JPanel();
        jLabel140 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        btnInc42 = new javax.swing.JButton();
        btnDec42 = new javax.swing.JButton();
        jTextField42 = new javax.swing.JTextField();
        b6 = new javax.swing.JTextField();
        jLabel142 = new javax.swing.JLabel();
        jPanel110 = new javax.swing.JPanel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        btnInc43 = new javax.swing.JButton();
        btnDec43 = new javax.swing.JButton();
        jTextField43 = new javax.swing.JTextField();
        b5 = new javax.swing.JTextField();
        jLabel145 = new javax.swing.JLabel();
        jPanel111 = new javax.swing.JPanel();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        btnInc44 = new javax.swing.JButton();
        btnDec44 = new javax.swing.JButton();
        jTextField44 = new javax.swing.JTextField();
        b4 = new javax.swing.JTextField();
        jLabel148 = new javax.swing.JLabel();
        jPanel112 = new javax.swing.JPanel();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        btnInc45 = new javax.swing.JButton();
        btnDec45 = new javax.swing.JButton();
        jTextField45 = new javax.swing.JTextField();
        b3 = new javax.swing.JTextField();
        jLabel151 = new javax.swing.JLabel();
        jPanel113 = new javax.swing.JPanel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        btnInc46 = new javax.swing.JButton();
        btnDec46 = new javax.swing.JButton();
        jTextField46 = new javax.swing.JTextField();
        b2 = new javax.swing.JTextField();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        jPanel114 = new javax.swing.JPanel();
        jlblSp8 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        btnInc47 = new javax.swing.JButton();
        btnDec47 = new javax.swing.JButton();
        jTextField47 = new javax.swing.JTextField();
        b1 = new javax.swing.JTextField();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel85 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        btnInc18 = new javax.swing.JButton();
        btnDec18 = new javax.swing.JButton();
        jTextField18 = new javax.swing.JTextField();
        mb6 = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jPanel86 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        btnInc19 = new javax.swing.JButton();
        btnDec19 = new javax.swing.JButton();
        jTextField19 = new javax.swing.JTextField();
        mb5 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jPanel87 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        btnInc20 = new javax.swing.JButton();
        btnDec20 = new javax.swing.JButton();
        jTextField20 = new javax.swing.JTextField();
        mb4 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jPanel88 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        btnInc21 = new javax.swing.JButton();
        btnDec21 = new javax.swing.JButton();
        jTextField21 = new javax.swing.JTextField();
        mb3 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jPanel89 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        btnInc22 = new javax.swing.JButton();
        btnDec22 = new javax.swing.JButton();
        jTextField22 = new javax.swing.JTextField();
        mb2 = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jPanel90 = new javax.swing.JPanel();
        jlblSp4 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        btnInc23 = new javax.swing.JButton();
        btnDec23 = new javax.swing.JButton();
        jTextField23 = new javax.swing.JTextField();
        mb1 = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel91 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        btnInc24 = new javax.swing.JButton();
        btnDec24 = new javax.swing.JButton();
        jTextField24 = new javax.swing.JTextField();
        h6 = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        jPanel92 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        btnInc25 = new javax.swing.JButton();
        btnDec25 = new javax.swing.JButton();
        jTextField25 = new javax.swing.JTextField();
        h5 = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        jPanel93 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        btnInc26 = new javax.swing.JButton();
        btnDec26 = new javax.swing.JButton();
        jTextField26 = new javax.swing.JTextField();
        h4 = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jPanel94 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        btnInc27 = new javax.swing.JButton();
        btnDec27 = new javax.swing.JButton();
        jTextField27 = new javax.swing.JTextField();
        h3 = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        jPanel95 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        btnInc28 = new javax.swing.JButton();
        btnDec28 = new javax.swing.JButton();
        jTextField28 = new javax.swing.JTextField();
        h2 = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jPanel96 = new javax.swing.JPanel();
        jlblSp5 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        btnInc29 = new javax.swing.JButton();
        btnDec29 = new javax.swing.JButton();
        jTextField29 = new javax.swing.JTextField();
        h1 = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel115 = new javax.swing.JPanel();
        jLabel159 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        btnInc48 = new javax.swing.JButton();
        btnDec48 = new javax.swing.JButton();
        jTextField48 = new javax.swing.JTextField();
        g6 = new javax.swing.JTextField();
        jLabel161 = new javax.swing.JLabel();
        jPanel116 = new javax.swing.JPanel();
        jLabel162 = new javax.swing.JLabel();
        jLabel163 = new javax.swing.JLabel();
        btnInc49 = new javax.swing.JButton();
        btnDec49 = new javax.swing.JButton();
        jTextField49 = new javax.swing.JTextField();
        g5 = new javax.swing.JTextField();
        jLabel164 = new javax.swing.JLabel();
        jPanel117 = new javax.swing.JPanel();
        jLabel165 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        btnInc50 = new javax.swing.JButton();
        btnDec50 = new javax.swing.JButton();
        jTextField50 = new javax.swing.JTextField();
        g4 = new javax.swing.JTextField();
        jLabel167 = new javax.swing.JLabel();
        jPanel118 = new javax.swing.JPanel();
        jLabel168 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        btnInc51 = new javax.swing.JButton();
        btnDec51 = new javax.swing.JButton();
        jTextField51 = new javax.swing.JTextField();
        g3 = new javax.swing.JTextField();
        jLabel170 = new javax.swing.JLabel();
        jPanel119 = new javax.swing.JPanel();
        jLabel171 = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        btnInc52 = new javax.swing.JButton();
        btnDec52 = new javax.swing.JButton();
        jTextField52 = new javax.swing.JTextField();
        g2 = new javax.swing.JTextField();
        jLabel173 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        jPanel120 = new javax.swing.JPanel();
        jlblSp9 = new javax.swing.JLabel();
        jLabel175 = new javax.swing.JLabel();
        btnInc53 = new javax.swing.JButton();
        btnDec53 = new javax.swing.JButton();
        jTextField53 = new javax.swing.JTextField();
        g1 = new javax.swing.JTextField();
        jLabel176 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel97 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        btnInc30 = new javax.swing.JButton();
        btnDec30 = new javax.swing.JButton();
        jTextField30 = new javax.swing.JTextField();
        d6 = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        jPanel98 = new javax.swing.JPanel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        btnInc31 = new javax.swing.JButton();
        btnDec31 = new javax.swing.JButton();
        jTextField31 = new javax.swing.JTextField();
        d5 = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        jPanel99 = new javax.swing.JPanel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        btnInc32 = new javax.swing.JButton();
        btnDec32 = new javax.swing.JButton();
        jTextField32 = new javax.swing.JTextField();
        d4 = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        jPanel100 = new javax.swing.JPanel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        btnInc33 = new javax.swing.JButton();
        btnDec33 = new javax.swing.JButton();
        jTextField33 = new javax.swing.JTextField();
        d3 = new javax.swing.JTextField();
        jLabel113 = new javax.swing.JLabel();
        jPanel101 = new javax.swing.JPanel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        btnInc34 = new javax.swing.JButton();
        btnDec34 = new javax.swing.JButton();
        jTextField34 = new javax.swing.JTextField();
        d2 = new javax.swing.JTextField();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jPanel102 = new javax.swing.JPanel();
        jlblSp6 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        btnInc35 = new javax.swing.JButton();
        btnDec35 = new javax.swing.JButton();
        jTextField35 = new javax.swing.JTextField();
        d1 = new javax.swing.JTextField();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel103 = new javax.swing.JPanel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        btnInc36 = new javax.swing.JButton();
        btnDec36 = new javax.swing.JButton();
        jTextField36 = new javax.swing.JTextField();
        s6 = new javax.swing.JTextField();
        jLabel123 = new javax.swing.JLabel();
        jPanel104 = new javax.swing.JPanel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        btnInc37 = new javax.swing.JButton();
        btnDec37 = new javax.swing.JButton();
        jTextField37 = new javax.swing.JTextField();
        s5 = new javax.swing.JTextField();
        jLabel126 = new javax.swing.JLabel();
        jPanel105 = new javax.swing.JPanel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        btnInc38 = new javax.swing.JButton();
        btnDec38 = new javax.swing.JButton();
        jTextField38 = new javax.swing.JTextField();
        s4 = new javax.swing.JTextField();
        jLabel129 = new javax.swing.JLabel();
        jPanel106 = new javax.swing.JPanel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        btnInc39 = new javax.swing.JButton();
        btnDec39 = new javax.swing.JButton();
        jTextField39 = new javax.swing.JTextField();
        s3 = new javax.swing.JTextField();
        jLabel132 = new javax.swing.JLabel();
        jPanel107 = new javax.swing.JPanel();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        btnInc40 = new javax.swing.JButton();
        btnDec40 = new javax.swing.JButton();
        jTextField40 = new javax.swing.JTextField();
        s2 = new javax.swing.JTextField();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jPanel108 = new javax.swing.JPanel();
        jlblSp7 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        btnInc41 = new javax.swing.JButton();
        btnDec41 = new javax.swing.JButton();
        jTextField41 = new javax.swing.JTextField();
        s1 = new javax.swing.JTextField();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel74 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        btnInc7 = new javax.swing.JButton();
        btnDec7 = new javax.swing.JButton();
        jTextField12 = new javax.swing.JTextField();
        dr6 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jPanel80 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        btnInc13 = new javax.swing.JButton();
        btnDec13 = new javax.swing.JButton();
        jTextField13 = new javax.swing.JTextField();
        dr5 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jPanel81 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        btnInc14 = new javax.swing.JButton();
        btnDec14 = new javax.swing.JButton();
        jTextField14 = new javax.swing.JTextField();
        dr4 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jPanel82 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        btnInc15 = new javax.swing.JButton();
        btnDec15 = new javax.swing.JButton();
        jTextField15 = new javax.swing.JTextField();
        dr3 = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jPanel83 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        btnInc16 = new javax.swing.JButton();
        btnDec16 = new javax.swing.JButton();
        jTextField16 = new javax.swing.JTextField();
        dr2 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jPanel84 = new javax.swing.JPanel();
        jlblSp3 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        btnInc17 = new javax.swing.JButton();
        btnDec17 = new javax.swing.JButton();
        jTextField17 = new javax.swing.JTextField();
        dr1 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(239, 239, 239));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidebar.setBackground(new java.awt.Color(255, 255, 255));
        sidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("ORDERING SYSTEM");
        sidebar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, 20));

        btnSpBurger.setBackground(new java.awt.Color(37, 150, 190));
        btnSpBurger.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSpBurger.setForeground(java.awt.Color.white);
        btnSpBurger.setText("    Special Burgers");
        btnSpBurger.setBorder(null);
        btnSpBurger.setFocusPainted(false);
        btnSpBurger.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSpBurger.setInheritsPopupMenu(true);
        btnSpBurger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpBurgerActionPerformed(evt);
            }
        });
        sidebar.add(btnSpBurger, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 102, 224, 36));

        btnChckBurger.setBackground(new java.awt.Color(255, 255, 255));
        btnChckBurger.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChckBurger.setForeground(new java.awt.Color(0, 0, 0));
        btnChckBurger.setText("    Chicken Burgers");
        btnChckBurger.setBorder(null);
        btnChckBurger.setFocusPainted(false);
        btnChckBurger.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnChckBurger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChckBurgerActionPerformed(evt);
            }
        });
        sidebar.add(btnChckBurger, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 144, 224, 36));

        btnBurger.setBackground(new java.awt.Color(255, 255, 255));
        btnBurger.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBurger.setForeground(new java.awt.Color(0, 0, 0));
        btnBurger.setText("    Burgers");
        btnBurger.setBorder(null);
        btnBurger.setFocusPainted(false);
        btnBurger.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBurger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBurgerActionPerformed(evt);
            }
        });
        sidebar.add(btnBurger, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 186, 224, 36));

        btnMiniBurger.setBackground(new java.awt.Color(255, 255, 255));
        btnMiniBurger.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMiniBurger.setForeground(new java.awt.Color(0, 0, 0));
        btnMiniBurger.setText("    Mini Burgers");
        btnMiniBurger.setBorder(null);
        btnMiniBurger.setFocusPainted(false);
        btnMiniBurger.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMiniBurger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMiniBurgerActionPerformed(evt);
            }
        });
        sidebar.add(btnMiniBurger, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 228, 224, 36));

        btnHotdogs.setBackground(new java.awt.Color(255, 255, 255));
        btnHotdogs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHotdogs.setForeground(new java.awt.Color(0, 0, 0));
        btnHotdogs.setText("    Hotdogs");
        btnHotdogs.setBorder(null);
        btnHotdogs.setFocusPainted(false);
        btnHotdogs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHotdogs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHotdogsActionPerformed(evt);
            }
        });
        sidebar.add(btnHotdogs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 224, 36));

        btnGrills.setBackground(new java.awt.Color(255, 255, 255));
        btnGrills.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGrills.setForeground(new java.awt.Color(0, 0, 0));
        btnGrills.setText("    Grills");
        btnGrills.setBorder(null);
        btnGrills.setFocusPainted(false);
        btnGrills.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGrills.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrillsActionPerformed(evt);
            }
        });
        sidebar.add(btnGrills, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 312, 224, 36));

        btnDesserts.setBackground(new java.awt.Color(255, 255, 255));
        btnDesserts.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDesserts.setForeground(new java.awt.Color(0, 0, 0));
        btnDesserts.setText("    Desserts");
        btnDesserts.setBorder(null);
        btnDesserts.setFocusPainted(false);
        btnDesserts.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDesserts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDessertsActionPerformed(evt);
            }
        });
        sidebar.add(btnDesserts, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 354, 224, 36));

        btnSides.setBackground(new java.awt.Color(255, 255, 255));
        btnSides.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSides.setForeground(new java.awt.Color(0, 0, 0));
        btnSides.setText("    Sides");
        btnSides.setBorder(null);
        btnSides.setFocusPainted(false);
        btnSides.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSides.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSidesActionPerformed(evt);
            }
        });
        sidebar.add(btnSides, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 396, 224, 36));

        btnDrinks.setBackground(new java.awt.Color(255, 255, 255));
        btnDrinks.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDrinks.setForeground(new java.awt.Color(0, 0, 0));
        btnDrinks.setText("    Drinks");
        btnDrinks.setBorder(null);
        btnDrinks.setFocusPainted(false);
        btnDrinks.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDrinks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDrinksActionPerformed(evt);
            }
        });
        sidebar.add(btnDrinks, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 438, 224, 36));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Webalys-Kameleon.pics-Food-Dome.48.png"))); // NOI18N
        sidebar.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 50, 60));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("MENU");
        sidebar.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 60, 20));

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("FASTFOOD");
        sidebar.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 20));

        jPanel1.add(sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 630));

        sidebar3.setBackground(new java.awt.Color(255, 255, 255));
        sidebar3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("MY ORDER");
        sidebar3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 107, 36));

        jTableMyOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Qty", "Product", "Price", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableMyOrder.setEnabled(false);
        jTableMyOrder.setFocusable(false);
        jTableMyOrder.setShowGrid(false);
        jTableMyOrder.getTableHeader().setResizingAllowed(false);
        jTableMyOrder.getTableHeader().setReorderingAllowed(false);
        jTableMyOrder.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jTableMyOrderInputMethodTextChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMyOrder);
        if (jTableMyOrder.getColumnModel().getColumnCount() > 0) {
            jTableMyOrder.getColumnModel().getColumn(0).setResizable(false);
            jTableMyOrder.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableMyOrder.getColumnModel().getColumn(1).setMinWidth(60);
            jTableMyOrder.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTableMyOrder.getColumnModel().getColumn(1).setMaxWidth(60);
            jTableMyOrder.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );

        sidebar3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 48, 200, 290));

        btnOrderList.setBackground(new java.awt.Color(255, 102, 102));
        btnOrderList.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnOrderList.setForeground(new java.awt.Color(255, 255, 255));
        btnOrderList.setText("TOTAL");
        btnOrderList.setBorderPainted(false);
        btnOrderList.setFocusPainted(false);
        btnOrderList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderListActionPerformed(evt);
            }
        });

        btnPayNow1.setBackground(new java.awt.Color(0, 204, 51));
        btnPayNow1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnPayNow1.setForeground(new java.awt.Color(0, 0, 0));
        btnPayNow1.setText("PAY NOW");
        btnPayNow1.setBorderPainted(false);
        btnPayNow1.setFocusPainted(false);
        btnPayNow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayNow1ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Subtotal:");

        jtxtSubtotal.setEditable(false);
        jtxtSubtotal.setBackground(new java.awt.Color(255, 255, 255));
        jtxtSubtotal.setForeground(java.awt.Color.black);
        jtxtSubtotal.setText("Php ");
        jtxtSubtotal.setToolTipText("");
        jtxtSubtotal.setBorder(null);
        jtxtSubtotal.setOpaque(true);
        jtxtSubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtSubtotalActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("TAX:");

        jtxtTAX.setEditable(false);
        jtxtTAX.setBackground(new java.awt.Color(255, 255, 255));
        jtxtTAX.setForeground(new java.awt.Color(0, 0, 0));
        jtxtTAX.setText("Php ");
        jtxtTAX.setBorder(null);
        jtxtTAX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtTAXActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setText("Discount:");

        jtxtDiscount.setEditable(false);
        jtxtDiscount.setBackground(new java.awt.Color(255, 255, 255));
        jtxtDiscount.setForeground(new java.awt.Color(0, 0, 0));
        jtxtDiscount.setText("Php ");
        jtxtDiscount.setBorder(null);
        jtxtDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtDiscountActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setText("Total:");

        jtxtTotal.setEditable(false);
        jtxtTotal.setBackground(new java.awt.Color(255, 255, 255));
        jtxtTotal.setForeground(new java.awt.Color(0, 0, 0));
        jtxtTotal.setText("Php ");
        jtxtTotal.setBorder(null);
        jtxtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOrderList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPayNow1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtTAX, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 60, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnOrderList, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jtxtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jtxtTAX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jtxtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jtxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnPayNow1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        sidebar3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 345, -1, 200));

        jPanel5.setBackground(java.awt.Color.white);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOrderingViewExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Elegantthemes-Beautiful-Flat-Power.48.png"))); // NOI18N
        btnOrderingViewExit.setBorderPainted(false);
        btnOrderingViewExit.setContentAreaFilled(false);
        btnOrderingViewExit.setFocusPainted(false);
        btnOrderingViewExit.setFocusable(false);
        btnOrderingViewExit.setRequestFocusEnabled(false);
        btnOrderingViewExit.setRolloverEnabled(false);
        btnOrderingViewExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderingViewExitActionPerformed(evt);
            }
        });
        jPanel5.add(btnOrderingViewExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, -1));

        sidebar3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 552, 200, -1));

        jPanel1.add(sidebar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, -1, 630));

        jTabbedPane1.setBackground(new java.awt.Color(242, 242, 242));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel20.setBackground(new java.awt.Color(244, 244, 244));

        jPanel68.setBackground(java.awt.Color.white);
        jPanel68.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblSp1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlblSp1.setForeground(new java.awt.Color(51, 51, 51));
        jlblSp1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblSp1.setText("Aloha Pine Saucers");
        jPanel68.add(jlblSp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 204, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Php 300.00");
        jPanel68.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc1.setBorder(null);
        btnInc1.setFocusPainted(false);
        btnInc1.setFocusable(false);
        btnInc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc1ActionPerformed(evt);
            }
        });
        jPanel68.add(btnInc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec1.setBorder(null);
        btnDec1.setFocusPainted(false);
        btnDec1.setFocusable(false);
        btnDec1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec1ActionPerformed(evt);
            }
        });
        jPanel68.add(btnDec1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField1.setText("Quantity:");
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel68.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        jtxtQty11.setEditable(false);
        jtxtQty11.setBackground(new java.awt.Color(255, 255, 255));
        jtxtQty11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jtxtQty11.setForeground(new java.awt.Color(51, 153, 0));
        jtxtQty11.setText("0");
        jtxtQty11.setBorder(null);
        jtxtQty11.setFocusable(false);
        jtxtQty11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtQty11ActionPerformed(evt);
            }
        });
        jPanel68.add(jtxtQty11, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/sp/1.jpg"))); // NOI18N
        jPanel68.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));
        jPanel68.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, 189, 125));

        jPanel69.setBackground(java.awt.Color.white);
        jPanel69.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Beef Bacon Overload");
        jPanel69.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 203, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 204, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Php 320.00");
        jPanel69.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 203, -1));

        btnInc2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc2.setBorder(null);
        btnInc2.setFocusPainted(false);
        btnInc2.setFocusable(false);
        btnInc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc2ActionPerformed(evt);
            }
        });
        jPanel69.add(btnInc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 177, 35, 33));

        btnDec2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec2.setBorder(null);
        btnDec2.setFocusPainted(false);
        btnDec2.setFocusable(false);
        btnDec2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec2ActionPerformed(evt);
            }
        });
        jPanel69.add(btnDec2, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 177, 35, 33));

        jTextField3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField3.setText("Quantity:");
        jTextField3.setBorder(null);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel69.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        jtxtQty12.setEditable(false);
        jtxtQty12.setBackground(new java.awt.Color(255, 255, 255));
        jtxtQty12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jtxtQty12.setForeground(new java.awt.Color(51, 153, 0));
        jtxtQty12.setText("0");
        jtxtQty12.setBorder(null);
        jtxtQty12.setFocusable(false);
        jPanel69.add(jtxtQty12, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));
        jPanel69.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 0, -1, 125));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/sp/2.jpg"))); // NOI18N
        jPanel69.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel71.setBackground(java.awt.Color.white);
        jPanel71.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Black Double Hamburger");
        jPanel71.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 204, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Php 275.00");
        jPanel71.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc3.setBorder(null);
        btnInc3.setFocusPainted(false);
        btnInc3.setFocusable(false);
        btnInc3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc3ActionPerformed(evt);
            }
        });
        jPanel71.add(btnInc3, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec3.setBorder(null);
        btnDec3.setFocusPainted(false);
        btnDec3.setFocusable(false);
        btnDec3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec3ActionPerformed(evt);
            }
        });
        jPanel71.add(btnDec3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField7.setText("Quantity:");
        jTextField7.setBorder(null);
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jPanel71.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        jtxtQty13.setEditable(false);
        jtxtQty13.setBackground(new java.awt.Color(255, 255, 255));
        jtxtQty13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jtxtQty13.setForeground(new java.awt.Color(51, 153, 0));
        jtxtQty13.setText("0");
        jtxtQty13.setBorder(null);
        jtxtQty13.setFocusable(false);
        jtxtQty13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtQty13ActionPerformed(evt);
            }
        });
        jPanel71.add(jtxtQty13, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/sp/3.jpg"))); // NOI18N
        jPanel71.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel70.setBackground(java.awt.Color.white);
        jPanel70.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Double Korean");
        jPanel70.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 204, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Php 275.00");
        jPanel70.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc4.setBorder(null);
        btnInc4.setFocusPainted(false);
        btnInc4.setFocusable(false);
        btnInc4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc4ActionPerformed(evt);
            }
        });
        jPanel70.add(btnInc4, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec4.setBorder(null);
        btnDec4.setFocusPainted(false);
        btnDec4.setFocusable(false);
        btnDec4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec4ActionPerformed(evt);
            }
        });
        jPanel70.add(btnDec4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField5.setText("Quantity:");
        jTextField5.setBorder(null);
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel70.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        jtxtQty14.setEditable(false);
        jtxtQty14.setBackground(new java.awt.Color(255, 255, 255));
        jtxtQty14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jtxtQty14.setForeground(new java.awt.Color(51, 153, 0));
        jtxtQty14.setText("0");
        jtxtQty14.setBorder(null);
        jtxtQty14.setFocusable(false);
        jPanel70.add(jtxtQty14, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/sp/4.jpg"))); // NOI18N
        jPanel70.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel72.setBackground(java.awt.Color.white);
        jPanel72.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Persian Fried Chicken");
        jPanel72.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 204, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Php 290.00");
        jPanel72.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc5.setBorder(null);
        btnInc5.setFocusPainted(false);
        btnInc5.setFocusable(false);
        btnInc5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc5ActionPerformed(evt);
            }
        });
        jPanel72.add(btnInc5, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec5.setBorder(null);
        btnDec5.setFocusPainted(false);
        btnDec5.setFocusable(false);
        btnDec5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec5ActionPerformed(evt);
            }
        });
        jPanel72.add(btnDec5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField9.setText("Quantity:");
        jTextField9.setBorder(null);
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jPanel72.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        jtxtQty15.setEditable(false);
        jtxtQty15.setBackground(new java.awt.Color(255, 255, 255));
        jtxtQty15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jtxtQty15.setForeground(new java.awt.Color(51, 153, 0));
        jtxtQty15.setText("0");
        jtxtQty15.setBorder(null);
        jtxtQty15.setFocusable(false);
        jtxtQty15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtQty15ActionPerformed(evt);
            }
        });
        jPanel72.add(jtxtQty15, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/sp/5.jpg"))); // NOI18N
        jPanel72.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel73.setBackground(java.awt.Color.white);
        jPanel73.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Ruby Bun Hamburger");
        jPanel73.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 204, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Php 175.00");
        jPanel73.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc6.setBorder(null);
        btnInc6.setFocusPainted(false);
        btnInc6.setFocusable(false);
        btnInc6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc6ActionPerformed(evt);
            }
        });
        jPanel73.add(btnInc6, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec6.setBorder(null);
        btnDec6.setFocusPainted(false);
        btnDec6.setFocusable(false);
        btnDec6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec6ActionPerformed(evt);
            }
        });
        jPanel73.add(btnDec6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField11.setText("Quantity:");
        jTextField11.setBorder(null);
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        jPanel73.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        jtxtQty16.setEditable(false);
        jtxtQty16.setBackground(new java.awt.Color(255, 255, 255));
        jtxtQty16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jtxtQty16.setForeground(new java.awt.Color(51, 153, 0));
        jtxtQty16.setText("0");
        jtxtQty16.setBorder(null);
        jtxtQty16.setFocusable(false);
        jPanel73.add(jtxtQty16, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/sp/6.jpg"))); // NOI18N
        jPanel73.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(191, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab1", jPanel3);

        jPanel75.setBackground(java.awt.Color.white);
        jPanel75.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Regular Chicken Burger");
        jPanel75.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 204, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Php 129.00");
        jPanel75.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc8.setBorder(null);
        btnInc8.setFocusPainted(false);
        btnInc8.setFocusable(false);
        btnInc8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc8ActionPerformed(evt);
            }
        });
        jPanel75.add(btnInc8, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec8.setBorder(null);
        btnDec8.setFocusPainted(false);
        btnDec8.setFocusable(false);
        btnDec8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec8ActionPerformed(evt);
            }
        });
        jPanel75.add(btnDec8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField10.setText("Quantity:");
        jTextField10.setBorder(null);
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jPanel75.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        cb5.setEditable(false);
        cb5.setBackground(new java.awt.Color(255, 255, 255));
        cb5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cb5.setForeground(new java.awt.Color(51, 153, 0));
        cb5.setText("0");
        cb5.setBorder(null);
        cb5.setFocusable(false);
        cb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb5ActionPerformed(evt);
            }
        });
        jPanel75.add(cb5, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/cburger/5.jpg"))); // NOI18N
        jPanel75.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel76.setBackground(java.awt.Color.white);
        jPanel76.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Grilled Chicken Burger");
        jPanel76.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 204, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Php 129.00");
        jPanel76.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc9.setBorder(null);
        btnInc9.setFocusPainted(false);
        btnInc9.setFocusable(false);
        btnInc9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc9ActionPerformed(evt);
            }
        });
        jPanel76.add(btnInc9, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec9.setBorder(null);
        btnDec9.setFocusPainted(false);
        btnDec9.setFocusable(false);
        btnDec9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec9ActionPerformed(evt);
            }
        });
        jPanel76.add(btnDec9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField6.setText("Quantity:");
        jTextField6.setBorder(null);
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel76.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        cb4.setEditable(false);
        cb4.setBackground(new java.awt.Color(255, 255, 255));
        cb4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cb4.setForeground(new java.awt.Color(51, 153, 0));
        cb4.setText("0");
        cb4.setBorder(null);
        cb4.setFocusable(false);
        jPanel76.add(cb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/cburger/4.jpg"))); // NOI18N
        jPanel76.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel77.setBackground(java.awt.Color.white);
        jPanel77.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Spicy Chicken Burger");
        jPanel77.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 204, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Php 129.00");
        jPanel77.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc10.setBorder(null);
        btnInc10.setFocusPainted(false);
        btnInc10.setFocusable(false);
        btnInc10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc10ActionPerformed(evt);
            }
        });
        jPanel77.add(btnInc10, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec10.setBorder(null);
        btnDec10.setFocusPainted(false);
        btnDec10.setFocusable(false);
        btnDec10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec10ActionPerformed(evt);
            }
        });
        jPanel77.add(btnDec10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField8.setText("Quantity:");
        jTextField8.setBorder(null);
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jPanel77.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        cb3.setEditable(false);
        cb3.setBackground(new java.awt.Color(255, 255, 255));
        cb3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cb3.setForeground(new java.awt.Color(51, 153, 0));
        cb3.setText("0");
        cb3.setBorder(null);
        cb3.setFocusable(false);
        cb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb3ActionPerformed(evt);
            }
        });
        jPanel77.add(cb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/cburger/3.jpg"))); // NOI18N
        jPanel77.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel78.setBackground(java.awt.Color.white);
        jPanel78.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Square Chicken Burger");
        jPanel78.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 203, -1));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 204, 255));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Php 129.00");
        jPanel78.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 203, -1));

        btnInc11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc11.setBorder(null);
        btnInc11.setFocusPainted(false);
        btnInc11.setFocusable(false);
        btnInc11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc11ActionPerformed(evt);
            }
        });
        jPanel78.add(btnInc11, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 177, 35, 33));

        btnDec11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec11.setBorder(null);
        btnDec11.setFocusPainted(false);
        btnDec11.setFocusable(false);
        btnDec11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec11ActionPerformed(evt);
            }
        });
        jPanel78.add(btnDec11, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 177, 35, 33));

        jTextField4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField4.setText("Quantity:");
        jTextField4.setBorder(null);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel78.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        cb2.setEditable(false);
        cb2.setBackground(new java.awt.Color(255, 255, 255));
        cb2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cb2.setForeground(new java.awt.Color(51, 153, 0));
        cb2.setText("0");
        cb2.setBorder(null);
        cb2.setFocusable(false);
        cb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb2ActionPerformed(evt);
            }
        });
        jPanel78.add(cb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));
        jPanel78.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 0, -1, 125));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/cburger/2.jpg"))); // NOI18N
        jPanel78.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel79.setBackground(java.awt.Color.white);
        jPanel79.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblSp2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlblSp2.setForeground(new java.awt.Color(51, 51, 51));
        jlblSp2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblSp2.setText("Turkey Burger");
        jPanel79.add(jlblSp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 204, 255));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Php 129.00");
        jPanel79.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc12.setBorder(null);
        btnInc12.setFocusPainted(false);
        btnInc12.setFocusable(false);
        btnInc12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc12ActionPerformed(evt);
            }
        });
        jPanel79.add(btnInc12, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec12.setBorder(null);
        btnDec12.setFocusPainted(false);
        btnDec12.setFocusable(false);
        btnDec12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec12ActionPerformed(evt);
            }
        });
        jPanel79.add(btnDec12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField2.setText("Quantity:");
        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel79.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        cb1.setEditable(false);
        cb1.setBackground(new java.awt.Color(255, 255, 255));
        cb1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cb1.setForeground(new java.awt.Color(51, 153, 0));
        cb1.setText("0");
        cb1.setBorder(null);
        cb1.setFocusable(false);
        cb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb1ActionPerformed(evt);
            }
        });
        jPanel79.add(cb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/cburger/1.jpg"))); // NOI18N
        jPanel79.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));
        jPanel79.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, 189, 125));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel78, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(191, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel9);

        jPanel109.setBackground(java.awt.Color.white);
        jPanel109.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel140.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel140.setForeground(new java.awt.Color(51, 51, 51));
        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel140.setText("King's Pride Deluxe");
        jPanel109.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel141.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel141.setForeground(new java.awt.Color(0, 204, 255));
        jLabel141.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel141.setText("Php 121.00");
        jPanel109.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc42.setBorder(null);
        btnInc42.setFocusPainted(false);
        btnInc42.setFocusable(false);
        btnInc42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc42ActionPerformed(evt);
            }
        });
        jPanel109.add(btnInc42, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec42.setBorder(null);
        btnDec42.setFocusPainted(false);
        btnDec42.setFocusable(false);
        btnDec42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec42ActionPerformed(evt);
            }
        });
        jPanel109.add(btnDec42, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField42.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField42.setText("Quantity:");
        jTextField42.setBorder(null);
        jTextField42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField42ActionPerformed(evt);
            }
        });
        jPanel109.add(jTextField42, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        b6.setEditable(false);
        b6.setBackground(new java.awt.Color(255, 255, 255));
        b6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b6.setForeground(new java.awt.Color(51, 153, 0));
        b6.setText("0");
        b6.setBorder(null);
        b6.setFocusable(false);
        jPanel109.add(b6, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel142.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/burger/6.jpg"))); // NOI18N
        jPanel109.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel110.setBackground(java.awt.Color.white);
        jPanel110.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel143.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel143.setForeground(new java.awt.Color(51, 51, 51));
        jLabel143.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel143.setText("Double Cheese Burger");
        jPanel110.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel144.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel144.setForeground(new java.awt.Color(0, 204, 255));
        jLabel144.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel144.setText("Php 121.00");
        jPanel110.add(jLabel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc43.setBorder(null);
        btnInc43.setFocusPainted(false);
        btnInc43.setFocusable(false);
        btnInc43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc43ActionPerformed(evt);
            }
        });
        jPanel110.add(btnInc43, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec43.setBorder(null);
        btnDec43.setFocusPainted(false);
        btnDec43.setFocusable(false);
        btnDec43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec43ActionPerformed(evt);
            }
        });
        jPanel110.add(btnDec43, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField43.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField43.setText("Quantity:");
        jTextField43.setBorder(null);
        jTextField43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField43ActionPerformed(evt);
            }
        });
        jPanel110.add(jTextField43, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        b5.setEditable(false);
        b5.setBackground(new java.awt.Color(255, 255, 255));
        b5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b5.setForeground(new java.awt.Color(51, 153, 0));
        b5.setText("0");
        b5.setBorder(null);
        b5.setFocusable(false);
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        jPanel110.add(b5, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel145.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/burger/5.jpg"))); // NOI18N
        jPanel110.add(jLabel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel111.setBackground(java.awt.Color.white);
        jPanel111.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel146.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel146.setForeground(new java.awt.Color(51, 51, 51));
        jLabel146.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel146.setText("Double Tower Patty");
        jPanel111.add(jLabel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel147.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel147.setForeground(new java.awt.Color(0, 204, 255));
        jLabel147.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel147.setText("Php 121.00");
        jPanel111.add(jLabel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc44.setBorder(null);
        btnInc44.setFocusPainted(false);
        btnInc44.setFocusable(false);
        btnInc44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc44ActionPerformed(evt);
            }
        });
        jPanel111.add(btnInc44, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec44.setBorder(null);
        btnDec44.setFocusPainted(false);
        btnDec44.setFocusable(false);
        btnDec44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec44ActionPerformed(evt);
            }
        });
        jPanel111.add(btnDec44, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField44.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField44.setText("Quantity:");
        jTextField44.setBorder(null);
        jTextField44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField44ActionPerformed(evt);
            }
        });
        jPanel111.add(jTextField44, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        b4.setEditable(false);
        b4.setBackground(new java.awt.Color(255, 255, 255));
        b4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b4.setForeground(new java.awt.Color(51, 153, 0));
        b4.setText("0");
        b4.setBorder(null);
        b4.setFocusable(false);
        jPanel111.add(b4, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel148.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/burger/4.jpg"))); // NOI18N
        jPanel111.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel112.setBackground(java.awt.Color.white);
        jPanel112.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel149.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel149.setForeground(new java.awt.Color(51, 51, 51));
        jLabel149.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel149.setText("Double Beef Patty");
        jPanel112.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel150.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel150.setForeground(new java.awt.Color(0, 204, 255));
        jLabel150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel150.setText("Php 121.00");
        jPanel112.add(jLabel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc45.setBorder(null);
        btnInc45.setFocusPainted(false);
        btnInc45.setFocusable(false);
        btnInc45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc45ActionPerformed(evt);
            }
        });
        jPanel112.add(btnInc45, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec45.setBorder(null);
        btnDec45.setFocusPainted(false);
        btnDec45.setFocusable(false);
        btnDec45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec45ActionPerformed(evt);
            }
        });
        jPanel112.add(btnDec45, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField45.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField45.setText("Quantity:");
        jTextField45.setBorder(null);
        jTextField45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField45ActionPerformed(evt);
            }
        });
        jPanel112.add(jTextField45, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        b3.setEditable(false);
        b3.setBackground(new java.awt.Color(255, 255, 255));
        b3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b3.setForeground(new java.awt.Color(51, 153, 0));
        b3.setText("0");
        b3.setBorder(null);
        b3.setFocusable(false);
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });
        jPanel112.add(b3, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel151.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/burger/3.jpg"))); // NOI18N
        jPanel112.add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel113.setBackground(java.awt.Color.white);
        jPanel113.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel152.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel152.setForeground(new java.awt.Color(51, 51, 51));
        jLabel152.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel152.setText("Cheese Burger");
        jPanel113.add(jLabel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 203, -1));

        jLabel153.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel153.setForeground(new java.awt.Color(0, 204, 255));
        jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel153.setText("Php 121.00");
        jPanel113.add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 203, -1));

        btnInc46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc46.setBorder(null);
        btnInc46.setFocusPainted(false);
        btnInc46.setFocusable(false);
        btnInc46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc46ActionPerformed(evt);
            }
        });
        jPanel113.add(btnInc46, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 177, 35, 33));

        btnDec46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec46.setBorder(null);
        btnDec46.setFocusPainted(false);
        btnDec46.setFocusable(false);
        btnDec46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec46ActionPerformed(evt);
            }
        });
        jPanel113.add(btnDec46, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 177, 35, 33));

        jTextField46.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField46.setText("Quantity:");
        jTextField46.setBorder(null);
        jTextField46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField46ActionPerformed(evt);
            }
        });
        jPanel113.add(jTextField46, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        b2.setEditable(false);
        b2.setBackground(new java.awt.Color(255, 255, 255));
        b2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b2.setForeground(new java.awt.Color(51, 153, 0));
        b2.setText("0");
        b2.setBorder(null);
        b2.setFocusable(false);
        jPanel113.add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));
        jPanel113.add(jLabel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 0, -1, 125));

        jLabel155.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/burger/2.jpg"))); // NOI18N
        jPanel113.add(jLabel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel114.setBackground(java.awt.Color.white);
        jPanel114.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblSp8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlblSp8.setForeground(new java.awt.Color(51, 51, 51));
        jlblSp8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblSp8.setText("Beef Egg Hamburger");
        jPanel114.add(jlblSp8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel156.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel156.setForeground(new java.awt.Color(0, 204, 255));
        jLabel156.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel156.setText("Php 121.00");
        jPanel114.add(jLabel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc47.setBorder(null);
        btnInc47.setFocusPainted(false);
        btnInc47.setFocusable(false);
        btnInc47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc47ActionPerformed(evt);
            }
        });
        jPanel114.add(btnInc47, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec47.setBorder(null);
        btnDec47.setFocusPainted(false);
        btnDec47.setFocusable(false);
        btnDec47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec47ActionPerformed(evt);
            }
        });
        jPanel114.add(btnDec47, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField47.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField47.setText("Quantity:");
        jTextField47.setBorder(null);
        jTextField47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField47ActionPerformed(evt);
            }
        });
        jPanel114.add(jTextField47, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        b1.setEditable(false);
        b1.setBackground(new java.awt.Color(255, 255, 255));
        b1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b1.setForeground(new java.awt.Color(51, 153, 0));
        b1.setText("0");
        b1.setBorder(null);
        b1.setFocusable(false);
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        jPanel114.add(b1, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel157.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/burger/1.jpg"))); // NOI18N
        jPanel114.add(jLabel157, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));
        jPanel114.add(jLabel158, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, 189, 125));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel112, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel111, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel110, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel109, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel112, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel109, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel110, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel111, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(191, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel6);

        jPanel85.setBackground(java.awt.Color.white);
        jPanel85.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(51, 51, 51));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("Grilled Mini Burger");
        jPanel85.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 204, 255));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("Php 150.00");
        jPanel85.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc18.setBorder(null);
        btnInc18.setFocusPainted(false);
        btnInc18.setFocusable(false);
        btnInc18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc18ActionPerformed(evt);
            }
        });
        jPanel85.add(btnInc18, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec18.setBorder(null);
        btnDec18.setFocusPainted(false);
        btnDec18.setFocusable(false);
        btnDec18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec18ActionPerformed(evt);
            }
        });
        jPanel85.add(btnDec18, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField18.setText("Quantity:");
        jTextField18.setBorder(null);
        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });
        jPanel85.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        mb6.setEditable(false);
        mb6.setBackground(new java.awt.Color(255, 255, 255));
        mb6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mb6.setForeground(new java.awt.Color(51, 153, 0));
        mb6.setText("0");
        mb6.setBorder(null);
        mb6.setFocusable(false);
        jPanel85.add(mb6, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/mburger/6.jpg"))); // NOI18N
        jPanel85.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel86.setBackground(java.awt.Color.white);
        jPanel86.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(51, 51, 51));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("Mini Beef Hamburger");
        jPanel86.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 204, 255));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("Php 150.00");
        jPanel86.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc19.setBorder(null);
        btnInc19.setFocusPainted(false);
        btnInc19.setFocusable(false);
        btnInc19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc19ActionPerformed(evt);
            }
        });
        jPanel86.add(btnInc19, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec19.setBorder(null);
        btnDec19.setFocusPainted(false);
        btnDec19.setFocusable(false);
        btnDec19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec19ActionPerformed(evt);
            }
        });
        jPanel86.add(btnDec19, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField19.setText("Quantity:");
        jTextField19.setBorder(null);
        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });
        jPanel86.add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        mb5.setEditable(false);
        mb5.setBackground(new java.awt.Color(255, 255, 255));
        mb5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mb5.setForeground(new java.awt.Color(51, 153, 0));
        mb5.setText("0");
        mb5.setBorder(null);
        mb5.setFocusable(false);
        mb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mb5ActionPerformed(evt);
            }
        });
        jPanel86.add(mb5, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/mburger/5.jpg"))); // NOI18N
        jPanel86.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel87.setBackground(java.awt.Color.white);
        jPanel87.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(51, 51, 51));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("Mini Burger Deluxe");
        jPanel87.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel71.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 204, 255));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("Php 150.00");
        jPanel87.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc20.setBorder(null);
        btnInc20.setFocusPainted(false);
        btnInc20.setFocusable(false);
        btnInc20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc20ActionPerformed(evt);
            }
        });
        jPanel87.add(btnInc20, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec20.setBorder(null);
        btnDec20.setFocusPainted(false);
        btnDec20.setFocusable(false);
        btnDec20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec20ActionPerformed(evt);
            }
        });
        jPanel87.add(btnDec20, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField20.setText("Quantity:");
        jTextField20.setBorder(null);
        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });
        jPanel87.add(jTextField20, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        mb4.setEditable(false);
        mb4.setBackground(new java.awt.Color(255, 255, 255));
        mb4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mb4.setForeground(new java.awt.Color(51, 153, 0));
        mb4.setText("0");
        mb4.setBorder(null);
        mb4.setFocusable(false);
        jPanel87.add(mb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/mburger/4.jpg"))); // NOI18N
        jPanel87.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel88.setBackground(java.awt.Color.white);
        jPanel88.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel73.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(51, 51, 51));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setText("Mini Cheese Overload");
        jPanel88.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 204, 255));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("Php 150.00");
        jPanel88.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc21.setBorder(null);
        btnInc21.setFocusPainted(false);
        btnInc21.setFocusable(false);
        btnInc21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc21ActionPerformed(evt);
            }
        });
        jPanel88.add(btnInc21, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec21.setBorder(null);
        btnDec21.setFocusPainted(false);
        btnDec21.setFocusable(false);
        btnDec21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec21ActionPerformed(evt);
            }
        });
        jPanel88.add(btnDec21, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField21.setText("Quantity:");
        jTextField21.setBorder(null);
        jTextField21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField21ActionPerformed(evt);
            }
        });
        jPanel88.add(jTextField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        mb3.setEditable(false);
        mb3.setBackground(new java.awt.Color(255, 255, 255));
        mb3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mb3.setForeground(new java.awt.Color(51, 153, 0));
        mb3.setText("0");
        mb3.setBorder(null);
        mb3.setFocusable(false);
        mb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mb3ActionPerformed(evt);
            }
        });
        jPanel88.add(mb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/mburger/3.jpg"))); // NOI18N
        jPanel88.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel89.setBackground(java.awt.Color.white);
        jPanel89.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(51, 51, 51));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("Mini Ruby Bun Burger");
        jPanel89.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 203, -1));

        jLabel77.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(0, 204, 255));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("Php 150.00");
        jPanel89.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 203, -1));

        btnInc22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc22.setBorder(null);
        btnInc22.setFocusPainted(false);
        btnInc22.setFocusable(false);
        btnInc22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc22ActionPerformed(evt);
            }
        });
        jPanel89.add(btnInc22, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 177, 35, 33));

        btnDec22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec22.setBorder(null);
        btnDec22.setFocusPainted(false);
        btnDec22.setFocusable(false);
        btnDec22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec22ActionPerformed(evt);
            }
        });
        jPanel89.add(btnDec22, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 177, 35, 33));

        jTextField22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField22.setText("Quantity:");
        jTextField22.setBorder(null);
        jTextField22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField22ActionPerformed(evt);
            }
        });
        jPanel89.add(jTextField22, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        mb2.setEditable(false);
        mb2.setBackground(new java.awt.Color(255, 255, 255));
        mb2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mb2.setForeground(new java.awt.Color(51, 153, 0));
        mb2.setText("0");
        mb2.setBorder(null);
        mb2.setFocusable(false);
        jPanel89.add(mb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));
        jPanel89.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 0, -1, 125));

        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/mburger/2.jpg"))); // NOI18N
        jPanel89.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel90.setBackground(java.awt.Color.white);
        jPanel90.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblSp4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlblSp4.setForeground(new java.awt.Color(51, 51, 51));
        jlblSp4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblSp4.setText("Mini Veggie Burger");
        jPanel90.add(jlblSp4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel80.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(0, 204, 255));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("Php 150.00");
        jPanel90.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc23.setBorder(null);
        btnInc23.setFocusPainted(false);
        btnInc23.setFocusable(false);
        btnInc23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc23ActionPerformed(evt);
            }
        });
        jPanel90.add(btnInc23, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec23.setBorder(null);
        btnDec23.setFocusPainted(false);
        btnDec23.setFocusable(false);
        btnDec23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec23ActionPerformed(evt);
            }
        });
        jPanel90.add(btnDec23, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField23.setText("Quantity:");
        jTextField23.setBorder(null);
        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });
        jPanel90.add(jTextField23, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        mb1.setEditable(false);
        mb1.setBackground(new java.awt.Color(255, 255, 255));
        mb1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mb1.setForeground(new java.awt.Color(51, 153, 0));
        mb1.setText("0");
        mb1.setBorder(null);
        mb1.setFocusable(false);
        mb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mb1ActionPerformed(evt);
            }
        });
        jPanel90.add(mb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/mburger/1.jpg"))); // NOI18N
        jPanel90.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));
        jPanel90.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, 189, 125));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel90, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel89, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel88, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel87, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel86, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel88, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel89, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel90, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel86, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel87, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(191, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanel10);

        jPanel91.setBackground(java.awt.Color.white);
        jPanel91.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel83.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(51, 51, 51));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("Long Hot-Bacon Deluxe");
        jPanel91.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel84.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(0, 204, 255));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("Php 75.00");
        jPanel91.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc24.setBorder(null);
        btnInc24.setFocusPainted(false);
        btnInc24.setFocusable(false);
        btnInc24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc24ActionPerformed(evt);
            }
        });
        jPanel91.add(btnInc24, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec24.setBorder(null);
        btnDec24.setFocusPainted(false);
        btnDec24.setFocusable(false);
        btnDec24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec24ActionPerformed(evt);
            }
        });
        jPanel91.add(btnDec24, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField24.setText("Quantity:");
        jTextField24.setBorder(null);
        jTextField24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField24ActionPerformed(evt);
            }
        });
        jPanel91.add(jTextField24, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        h6.setEditable(false);
        h6.setBackground(new java.awt.Color(255, 255, 255));
        h6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        h6.setForeground(new java.awt.Color(51, 153, 0));
        h6.setText("0");
        h6.setBorder(null);
        h6.setFocusable(false);
        jPanel91.add(h6, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/hotdogs/6.jpg"))); // NOI18N
        jPanel91.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel92.setBackground(java.awt.Color.white);
        jPanel92.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel86.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(51, 51, 51));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setText("Hotdog Sandwich");
        jPanel92.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel87.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(0, 204, 255));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("Php 75.00");
        jPanel92.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc25.setBorder(null);
        btnInc25.setFocusPainted(false);
        btnInc25.setFocusable(false);
        btnInc25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc25ActionPerformed(evt);
            }
        });
        jPanel92.add(btnInc25, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec25.setBorder(null);
        btnDec25.setFocusPainted(false);
        btnDec25.setFocusable(false);
        btnDec25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec25ActionPerformed(evt);
            }
        });
        jPanel92.add(btnDec25, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField25.setText("Quantity:");
        jTextField25.setBorder(null);
        jTextField25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField25ActionPerformed(evt);
            }
        });
        jPanel92.add(jTextField25, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        h5.setEditable(false);
        h5.setBackground(new java.awt.Color(255, 255, 255));
        h5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        h5.setForeground(new java.awt.Color(51, 153, 0));
        h5.setText("0");
        h5.setBorder(null);
        h5.setFocusable(false);
        h5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h5ActionPerformed(evt);
            }
        });
        jPanel92.add(h5, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/hotdogs/5.jpg"))); // NOI18N
        jPanel92.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel93.setBackground(java.awt.Color.white);
        jPanel93.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel89.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(51, 51, 51));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel89.setText("Hot-Bacon Dog");
        jPanel93.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel90.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 204, 255));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("Php 75.00");
        jPanel93.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc26.setBorder(null);
        btnInc26.setFocusPainted(false);
        btnInc26.setFocusable(false);
        btnInc26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc26ActionPerformed(evt);
            }
        });
        jPanel93.add(btnInc26, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec26.setBorder(null);
        btnDec26.setFocusPainted(false);
        btnDec26.setFocusable(false);
        btnDec26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec26ActionPerformed(evt);
            }
        });
        jPanel93.add(btnDec26, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField26.setText("Quantity:");
        jTextField26.setBorder(null);
        jTextField26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField26ActionPerformed(evt);
            }
        });
        jPanel93.add(jTextField26, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        h4.setEditable(false);
        h4.setBackground(new java.awt.Color(255, 255, 255));
        h4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        h4.setForeground(new java.awt.Color(51, 153, 0));
        h4.setText("0");
        h4.setBorder(null);
        h4.setFocusable(false);
        jPanel93.add(h4, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/hotdogs/4.jpg"))); // NOI18N
        jPanel93.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel94.setBackground(java.awt.Color.white);
        jPanel94.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel92.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(51, 51, 51));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel92.setText("Chicken Long Deluxe");
        jPanel94.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel93.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(0, 204, 255));
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel93.setText("Php 75.00");
        jPanel94.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc27.setBorder(null);
        btnInc27.setFocusPainted(false);
        btnInc27.setFocusable(false);
        btnInc27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc27ActionPerformed(evt);
            }
        });
        jPanel94.add(btnInc27, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec27.setBorder(null);
        btnDec27.setFocusPainted(false);
        btnDec27.setFocusable(false);
        btnDec27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec27ActionPerformed(evt);
            }
        });
        jPanel94.add(btnDec27, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField27.setText("Quantity:");
        jTextField27.setBorder(null);
        jTextField27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField27ActionPerformed(evt);
            }
        });
        jPanel94.add(jTextField27, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        h3.setEditable(false);
        h3.setBackground(new java.awt.Color(255, 255, 255));
        h3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        h3.setForeground(new java.awt.Color(51, 153, 0));
        h3.setText("0");
        h3.setBorder(null);
        h3.setFocusable(false);
        h3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h3ActionPerformed(evt);
            }
        });
        jPanel94.add(h3, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/hotdogs/3.jpg"))); // NOI18N
        jPanel94.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel95.setBackground(java.awt.Color.white);
        jPanel95.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel95.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(51, 51, 51));
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel95.setText("Cheesy Hungarian");
        jPanel95.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 203, -1));

        jLabel96.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(0, 204, 255));
        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel96.setText("Php 75.00");
        jPanel95.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 203, -1));

        btnInc28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc28.setBorder(null);
        btnInc28.setFocusPainted(false);
        btnInc28.setFocusable(false);
        btnInc28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc28ActionPerformed(evt);
            }
        });
        jPanel95.add(btnInc28, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 177, 35, 33));

        btnDec28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec28.setBorder(null);
        btnDec28.setFocusPainted(false);
        btnDec28.setFocusable(false);
        btnDec28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec28ActionPerformed(evt);
            }
        });
        jPanel95.add(btnDec28, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 177, 35, 33));

        jTextField28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField28.setText("Quantity:");
        jTextField28.setBorder(null);
        jTextField28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField28ActionPerformed(evt);
            }
        });
        jPanel95.add(jTextField28, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        h2.setEditable(false);
        h2.setBackground(new java.awt.Color(255, 255, 255));
        h2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        h2.setForeground(new java.awt.Color(51, 153, 0));
        h2.setText("0");
        h2.setBorder(null);
        h2.setFocusable(false);
        jPanel95.add(h2, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));
        jPanel95.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 0, -1, 125));

        jLabel98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/hotdogs/2.jpg"))); // NOI18N
        jPanel95.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel96.setBackground(java.awt.Color.white);
        jPanel96.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblSp5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlblSp5.setForeground(new java.awt.Color(51, 51, 51));
        jlblSp5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblSp5.setText("Cheesy Hotdog");
        jPanel96.add(jlblSp5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel99.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(0, 204, 255));
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel99.setText("Php 75.00");
        jPanel96.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc29.setBorder(null);
        btnInc29.setFocusPainted(false);
        btnInc29.setFocusable(false);
        btnInc29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc29ActionPerformed(evt);
            }
        });
        jPanel96.add(btnInc29, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec29.setBorder(null);
        btnDec29.setFocusPainted(false);
        btnDec29.setFocusable(false);
        btnDec29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec29ActionPerformed(evt);
            }
        });
        jPanel96.add(btnDec29, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField29.setText("Quantity:");
        jTextField29.setBorder(null);
        jTextField29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField29ActionPerformed(evt);
            }
        });
        jPanel96.add(jTextField29, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        h1.setEditable(false);
        h1.setBackground(new java.awt.Color(255, 255, 255));
        h1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        h1.setForeground(new java.awt.Color(51, 153, 0));
        h1.setText("0");
        h1.setBorder(null);
        h1.setFocusable(false);
        h1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h1ActionPerformed(evt);
            }
        });
        jPanel96.add(h1, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/hotdogs/1.jpg"))); // NOI18N
        jPanel96.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));
        jPanel96.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, 189, 125));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel95, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel94, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel91, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel94, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel95, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel91, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(191, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab5", jPanel14);

        jPanel115.setBackground(java.awt.Color.white);
        jPanel115.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel159.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel159.setForeground(new java.awt.Color(51, 51, 51));
        jLabel159.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel159.setText("Grilled Pork Belly");
        jPanel115.add(jLabel159, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel160.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel160.setForeground(new java.awt.Color(0, 204, 255));
        jLabel160.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel160.setText("Php 229.00");
        jPanel115.add(jLabel160, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc48.setBorder(null);
        btnInc48.setFocusPainted(false);
        btnInc48.setFocusable(false);
        btnInc48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc48ActionPerformed(evt);
            }
        });
        jPanel115.add(btnInc48, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec48.setBorder(null);
        btnDec48.setFocusPainted(false);
        btnDec48.setFocusable(false);
        btnDec48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec48ActionPerformed(evt);
            }
        });
        jPanel115.add(btnDec48, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField48.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField48.setText("Quantity:");
        jTextField48.setBorder(null);
        jTextField48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField48ActionPerformed(evt);
            }
        });
        jPanel115.add(jTextField48, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        g6.setEditable(false);
        g6.setBackground(new java.awt.Color(255, 255, 255));
        g6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        g6.setForeground(new java.awt.Color(51, 153, 0));
        g6.setText("0");
        g6.setBorder(null);
        g6.setFocusable(false);
        jPanel115.add(g6, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel161.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/grills/6.jpg"))); // NOI18N
        jPanel115.add(jLabel161, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel116.setBackground(java.awt.Color.white);
        jPanel116.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel162.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel162.setForeground(new java.awt.Color(51, 51, 51));
        jLabel162.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel162.setText("Grilled Beef Deluxe");
        jPanel116.add(jLabel162, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel163.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel163.setForeground(new java.awt.Color(0, 204, 255));
        jLabel163.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel163.setText("Php 229.00");
        jPanel116.add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc49.setBorder(null);
        btnInc49.setFocusPainted(false);
        btnInc49.setFocusable(false);
        btnInc49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc49ActionPerformed(evt);
            }
        });
        jPanel116.add(btnInc49, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec49.setBorder(null);
        btnDec49.setFocusPainted(false);
        btnDec49.setFocusable(false);
        btnDec49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec49ActionPerformed(evt);
            }
        });
        jPanel116.add(btnDec49, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField49.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField49.setText("Quantity:");
        jTextField49.setBorder(null);
        jTextField49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField49ActionPerformed(evt);
            }
        });
        jPanel116.add(jTextField49, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        g5.setEditable(false);
        g5.setBackground(new java.awt.Color(255, 255, 255));
        g5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        g5.setForeground(new java.awt.Color(51, 153, 0));
        g5.setText("0");
        g5.setBorder(null);
        g5.setFocusable(false);
        g5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g5ActionPerformed(evt);
            }
        });
        jPanel116.add(g5, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel164.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/grills/5.jpg"))); // NOI18N
        jPanel116.add(jLabel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel117.setBackground(java.awt.Color.white);
        jPanel117.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel165.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel165.setForeground(new java.awt.Color(51, 51, 51));
        jLabel165.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel165.setText("Grilled Chicken Leg");
        jPanel117.add(jLabel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel166.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel166.setForeground(new java.awt.Color(0, 204, 255));
        jLabel166.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel166.setText("Php 229.00");
        jPanel117.add(jLabel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc50.setBorder(null);
        btnInc50.setFocusPainted(false);
        btnInc50.setFocusable(false);
        btnInc50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc50ActionPerformed(evt);
            }
        });
        jPanel117.add(btnInc50, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec50.setBorder(null);
        btnDec50.setFocusPainted(false);
        btnDec50.setFocusable(false);
        btnDec50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec50ActionPerformed(evt);
            }
        });
        jPanel117.add(btnDec50, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField50.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField50.setText("Quantity:");
        jTextField50.setBorder(null);
        jTextField50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField50ActionPerformed(evt);
            }
        });
        jPanel117.add(jTextField50, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        g4.setEditable(false);
        g4.setBackground(new java.awt.Color(255, 255, 255));
        g4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        g4.setForeground(new java.awt.Color(51, 153, 0));
        g4.setText("0");
        g4.setBorder(null);
        g4.setFocusable(false);
        jPanel117.add(g4, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel167.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/grills/4.jpg"))); // NOI18N
        jPanel117.add(jLabel167, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel118.setBackground(java.awt.Color.white);
        jPanel118.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel168.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel168.setForeground(new java.awt.Color(51, 51, 51));
        jLabel168.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel168.setText("Grilled Burger Patty");
        jPanel118.add(jLabel168, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel169.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel169.setForeground(new java.awt.Color(0, 204, 255));
        jLabel169.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel169.setText("Php 229.00");
        jPanel118.add(jLabel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc51.setBorder(null);
        btnInc51.setFocusPainted(false);
        btnInc51.setFocusable(false);
        btnInc51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc51ActionPerformed(evt);
            }
        });
        jPanel118.add(btnInc51, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec51.setBorder(null);
        btnDec51.setFocusPainted(false);
        btnDec51.setFocusable(false);
        btnDec51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec51ActionPerformed(evt);
            }
        });
        jPanel118.add(btnDec51, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField51.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField51.setText("Quantity:");
        jTextField51.setBorder(null);
        jTextField51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField51ActionPerformed(evt);
            }
        });
        jPanel118.add(jTextField51, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        g3.setEditable(false);
        g3.setBackground(new java.awt.Color(255, 255, 255));
        g3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        g3.setForeground(new java.awt.Color(51, 153, 0));
        g3.setText("0");
        g3.setBorder(null);
        g3.setFocusable(false);
        g3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g3ActionPerformed(evt);
            }
        });
        jPanel118.add(g3, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel170.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/grills/3.jpg"))); // NOI18N
        jPanel118.add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel119.setBackground(java.awt.Color.white);
        jPanel119.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel171.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel171.setForeground(new java.awt.Color(51, 51, 51));
        jLabel171.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel171.setText("Grilled Seafood Salad");
        jPanel119.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 203, -1));

        jLabel172.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel172.setForeground(new java.awt.Color(0, 204, 255));
        jLabel172.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel172.setText("Php 229.00");
        jPanel119.add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 203, -1));

        btnInc52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc52.setBorder(null);
        btnInc52.setFocusPainted(false);
        btnInc52.setFocusable(false);
        btnInc52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc52ActionPerformed(evt);
            }
        });
        jPanel119.add(btnInc52, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 177, 35, 33));

        btnDec52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec52.setBorder(null);
        btnDec52.setFocusPainted(false);
        btnDec52.setFocusable(false);
        btnDec52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec52ActionPerformed(evt);
            }
        });
        jPanel119.add(btnDec52, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 177, 35, 33));

        jTextField52.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField52.setText("Quantity:");
        jTextField52.setBorder(null);
        jTextField52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField52ActionPerformed(evt);
            }
        });
        jPanel119.add(jTextField52, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        g2.setEditable(false);
        g2.setBackground(new java.awt.Color(255, 255, 255));
        g2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        g2.setForeground(new java.awt.Color(51, 153, 0));
        g2.setText("0");
        g2.setBorder(null);
        g2.setFocusable(false);
        jPanel119.add(g2, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));
        jPanel119.add(jLabel173, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 0, -1, 125));

        jLabel174.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/grills/2.jpg"))); // NOI18N
        jPanel119.add(jLabel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel120.setBackground(java.awt.Color.white);
        jPanel120.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblSp9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlblSp9.setForeground(new java.awt.Color(51, 51, 51));
        jlblSp9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblSp9.setText("Grilled Shrimp");
        jPanel120.add(jlblSp9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel175.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel175.setForeground(new java.awt.Color(0, 204, 255));
        jLabel175.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel175.setText("Php 229.00");
        jPanel120.add(jLabel175, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc53.setBorder(null);
        btnInc53.setFocusPainted(false);
        btnInc53.setFocusable(false);
        btnInc53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc53ActionPerformed(evt);
            }
        });
        jPanel120.add(btnInc53, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec53.setBorder(null);
        btnDec53.setFocusPainted(false);
        btnDec53.setFocusable(false);
        btnDec53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec53ActionPerformed(evt);
            }
        });
        jPanel120.add(btnDec53, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField53.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField53.setText("Quantity:");
        jTextField53.setBorder(null);
        jTextField53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField53ActionPerformed(evt);
            }
        });
        jPanel120.add(jTextField53, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        g1.setEditable(false);
        g1.setBackground(new java.awt.Color(255, 255, 255));
        g1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        g1.setForeground(new java.awt.Color(51, 153, 0));
        g1.setText("0");
        g1.setBorder(null);
        g1.setFocusable(false);
        g1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g1ActionPerformed(evt);
            }
        });
        jPanel120.add(g1, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel176.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/grills/1.jpg"))); // NOI18N
        jPanel120.add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));
        jPanel120.add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, 189, 125));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel120, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel119, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel118, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel117, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel116, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel115, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel118, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel119, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel120, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel115, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel116, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel117, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(191, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab6", jPanel15);

        jPanel97.setBackground(java.awt.Color.white);
        jPanel97.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel102.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(51, 51, 51));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setText("Cours De Boulangerie");
        jPanel97.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel103.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(0, 204, 255));
        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel103.setText("Php 100.00");
        jPanel97.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc30.setBorder(null);
        btnInc30.setFocusPainted(false);
        btnInc30.setFocusable(false);
        btnInc30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc30ActionPerformed(evt);
            }
        });
        jPanel97.add(btnInc30, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec30.setBorder(null);
        btnDec30.setFocusPainted(false);
        btnDec30.setFocusable(false);
        btnDec30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec30ActionPerformed(evt);
            }
        });
        jPanel97.add(btnDec30, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField30.setText("Quantity:");
        jTextField30.setBorder(null);
        jTextField30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField30ActionPerformed(evt);
            }
        });
        jPanel97.add(jTextField30, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        d6.setEditable(false);
        d6.setBackground(new java.awt.Color(255, 255, 255));
        d6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        d6.setForeground(new java.awt.Color(51, 153, 0));
        d6.setText("0");
        d6.setBorder(null);
        d6.setFocusable(false);
        jPanel97.add(d6, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/desserts/6.jpg"))); // NOI18N
        jPanel97.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel98.setBackground(java.awt.Color.white);
        jPanel98.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel105.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(51, 51, 51));
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel105.setText("Creamy Oreo Cake");
        jPanel98.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel106.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(0, 204, 255));
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel106.setText("Php 100.00");
        jPanel98.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc31.setBorder(null);
        btnInc31.setFocusPainted(false);
        btnInc31.setFocusable(false);
        btnInc31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc31ActionPerformed(evt);
            }
        });
        jPanel98.add(btnInc31, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec31.setBorder(null);
        btnDec31.setFocusPainted(false);
        btnDec31.setFocusable(false);
        btnDec31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec31ActionPerformed(evt);
            }
        });
        jPanel98.add(btnDec31, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField31.setText("Quantity:");
        jTextField31.setBorder(null);
        jTextField31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31ActionPerformed(evt);
            }
        });
        jPanel98.add(jTextField31, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        d5.setEditable(false);
        d5.setBackground(new java.awt.Color(255, 255, 255));
        d5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        d5.setForeground(new java.awt.Color(51, 153, 0));
        d5.setText("0");
        d5.setBorder(null);
        d5.setFocusable(false);
        d5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d5ActionPerformed(evt);
            }
        });
        jPanel98.add(d5, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel107.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/desserts/5.jpg"))); // NOI18N
        jPanel98.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel99.setBackground(java.awt.Color.white);
        jPanel99.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel108.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(51, 51, 51));
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel108.setText("Japanese Roll Cake");
        jPanel99.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel109.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(0, 204, 255));
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel109.setText("Php 100.00");
        jPanel99.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc32.setBorder(null);
        btnInc32.setFocusPainted(false);
        btnInc32.setFocusable(false);
        btnInc32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc32ActionPerformed(evt);
            }
        });
        jPanel99.add(btnInc32, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec32.setBorder(null);
        btnDec32.setFocusPainted(false);
        btnDec32.setFocusable(false);
        btnDec32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec32ActionPerformed(evt);
            }
        });
        jPanel99.add(btnDec32, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField32.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField32.setText("Quantity:");
        jTextField32.setBorder(null);
        jTextField32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField32ActionPerformed(evt);
            }
        });
        jPanel99.add(jTextField32, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        d4.setEditable(false);
        d4.setBackground(new java.awt.Color(255, 255, 255));
        d4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        d4.setForeground(new java.awt.Color(51, 153, 0));
        d4.setText("0");
        d4.setBorder(null);
        d4.setFocusable(false);
        jPanel99.add(d4, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel110.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/desserts/4.jpg"))); // NOI18N
        jPanel99.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel100.setBackground(java.awt.Color.white);
        jPanel100.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel111.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(51, 51, 51));
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel111.setText("Mini Chocolate Tarts");
        jPanel100.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel112.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(0, 204, 255));
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel112.setText("Php 100.00");
        jPanel100.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc33.setBorder(null);
        btnInc33.setFocusPainted(false);
        btnInc33.setFocusable(false);
        btnInc33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc33ActionPerformed(evt);
            }
        });
        jPanel100.add(btnInc33, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec33.setBorder(null);
        btnDec33.setFocusPainted(false);
        btnDec33.setFocusable(false);
        btnDec33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec33ActionPerformed(evt);
            }
        });
        jPanel100.add(btnDec33, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField33.setText("Quantity:");
        jTextField33.setBorder(null);
        jTextField33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField33ActionPerformed(evt);
            }
        });
        jPanel100.add(jTextField33, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        d3.setEditable(false);
        d3.setBackground(new java.awt.Color(255, 255, 255));
        d3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        d3.setForeground(new java.awt.Color(51, 153, 0));
        d3.setText("0");
        d3.setBorder(null);
        d3.setFocusable(false);
        d3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d3ActionPerformed(evt);
            }
        });
        jPanel100.add(d3, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel113.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/desserts/3.jpg"))); // NOI18N
        jPanel100.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel101.setBackground(java.awt.Color.white);
        jPanel101.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel114.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(51, 51, 51));
        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel114.setText("Strawberry Cheesecake");
        jPanel101.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 203, -1));

        jLabel115.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(0, 204, 255));
        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel115.setText("Php 100.00");
        jPanel101.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 203, -1));

        btnInc34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc34.setBorder(null);
        btnInc34.setFocusPainted(false);
        btnInc34.setFocusable(false);
        btnInc34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc34ActionPerformed(evt);
            }
        });
        jPanel101.add(btnInc34, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 177, 35, 33));

        btnDec34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec34.setBorder(null);
        btnDec34.setFocusPainted(false);
        btnDec34.setFocusable(false);
        btnDec34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec34ActionPerformed(evt);
            }
        });
        jPanel101.add(btnDec34, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 177, 35, 33));

        jTextField34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField34.setText("Quantity:");
        jTextField34.setBorder(null);
        jTextField34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField34ActionPerformed(evt);
            }
        });
        jPanel101.add(jTextField34, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        d2.setEditable(false);
        d2.setBackground(new java.awt.Color(255, 255, 255));
        d2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        d2.setForeground(new java.awt.Color(51, 153, 0));
        d2.setText("0");
        d2.setBorder(null);
        d2.setFocusable(false);
        d2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d2ActionPerformed(evt);
            }
        });
        jPanel101.add(d2, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));
        jPanel101.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 0, -1, 125));

        jLabel117.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/desserts/2.jpg"))); // NOI18N
        jPanel101.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel102.setBackground(java.awt.Color.white);
        jPanel102.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblSp6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlblSp6.setForeground(new java.awt.Color(51, 51, 51));
        jlblSp6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblSp6.setText("Tiramisu Cheesecake");
        jPanel102.add(jlblSp6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel118.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(0, 204, 255));
        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel118.setText("Php 100.00");
        jPanel102.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc35.setBorder(null);
        btnInc35.setFocusPainted(false);
        btnInc35.setFocusable(false);
        btnInc35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc35ActionPerformed(evt);
            }
        });
        jPanel102.add(btnInc35, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec35.setBorder(null);
        btnDec35.setFocusPainted(false);
        btnDec35.setFocusable(false);
        btnDec35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec35ActionPerformed(evt);
            }
        });
        jPanel102.add(btnDec35, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField35.setText("Quantity:");
        jTextField35.setBorder(null);
        jTextField35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField35ActionPerformed(evt);
            }
        });
        jPanel102.add(jTextField35, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        d1.setEditable(false);
        d1.setBackground(new java.awt.Color(255, 255, 255));
        d1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        d1.setForeground(new java.awt.Color(51, 153, 0));
        d1.setText("0");
        d1.setBorder(null);
        d1.setFocusable(false);
        d1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d1ActionPerformed(evt);
            }
        });
        jPanel102.add(d1, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel119.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/desserts/1.jpg"))); // NOI18N
        jPanel102.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));
        jPanel102.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, 189, 125));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel102, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel101, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel99, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel98, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel97, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel101, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel102, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel97, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel98, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel99, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(191, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab7", jPanel16);

        jPanel103.setBackground(java.awt.Color.white);
        jPanel103.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel121.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(51, 51, 51));
        jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel121.setText("Lasagna");
        jPanel103.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel122.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(0, 204, 255));
        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel122.setText("Php 75.00");
        jPanel103.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc36.setBorder(null);
        btnInc36.setFocusPainted(false);
        btnInc36.setFocusable(false);
        btnInc36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc36ActionPerformed(evt);
            }
        });
        jPanel103.add(btnInc36, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec36.setBorder(null);
        btnDec36.setFocusPainted(false);
        btnDec36.setFocusable(false);
        btnDec36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec36ActionPerformed(evt);
            }
        });
        jPanel103.add(btnDec36, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField36.setText("Quantity:");
        jTextField36.setBorder(null);
        jTextField36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField36ActionPerformed(evt);
            }
        });
        jPanel103.add(jTextField36, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        s6.setEditable(false);
        s6.setBackground(new java.awt.Color(255, 255, 255));
        s6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        s6.setForeground(new java.awt.Color(51, 153, 0));
        s6.setText("0");
        s6.setBorder(null);
        s6.setFocusable(false);
        jPanel103.add(s6, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel123.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/sides/6.jpg"))); // NOI18N
        jPanel103.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel104.setBackground(java.awt.Color.white);
        jPanel104.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel124.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel124.setForeground(new java.awt.Color(51, 51, 51));
        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel124.setText("Garden Salad Mix");
        jPanel104.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel125.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel125.setForeground(new java.awt.Color(0, 204, 255));
        jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel125.setText("Php 75.00");
        jPanel104.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc37.setBorder(null);
        btnInc37.setFocusPainted(false);
        btnInc37.setFocusable(false);
        btnInc37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc37ActionPerformed(evt);
            }
        });
        jPanel104.add(btnInc37, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec37.setBorder(null);
        btnDec37.setFocusPainted(false);
        btnDec37.setFocusable(false);
        btnDec37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec37ActionPerformed(evt);
            }
        });
        jPanel104.add(btnDec37, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField37.setText("Quantity:");
        jTextField37.setBorder(null);
        jTextField37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField37ActionPerformed(evt);
            }
        });
        jPanel104.add(jTextField37, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        s5.setEditable(false);
        s5.setBackground(new java.awt.Color(255, 255, 255));
        s5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        s5.setForeground(new java.awt.Color(51, 153, 0));
        s5.setText("0");
        s5.setBorder(null);
        s5.setFocusable(false);
        s5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s5ActionPerformed(evt);
            }
        });
        jPanel104.add(s5, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel126.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/sides/5.jpg"))); // NOI18N
        jPanel104.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel105.setBackground(java.awt.Color.white);
        jPanel105.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel127.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(51, 51, 51));
        jLabel127.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel127.setText("Crispy Fries");
        jPanel105.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel128.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel128.setForeground(new java.awt.Color(0, 204, 255));
        jLabel128.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel128.setText("Php 75.00");
        jPanel105.add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc38.setBorder(null);
        btnInc38.setFocusPainted(false);
        btnInc38.setFocusable(false);
        btnInc38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc38ActionPerformed(evt);
            }
        });
        jPanel105.add(btnInc38, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec38.setBorder(null);
        btnDec38.setFocusPainted(false);
        btnDec38.setFocusable(false);
        btnDec38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec38ActionPerformed(evt);
            }
        });
        jPanel105.add(btnDec38, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField38.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField38.setText("Quantity:");
        jTextField38.setBorder(null);
        jTextField38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField38ActionPerformed(evt);
            }
        });
        jPanel105.add(jTextField38, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        s4.setEditable(false);
        s4.setBackground(new java.awt.Color(255, 255, 255));
        s4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        s4.setForeground(new java.awt.Color(51, 153, 0));
        s4.setText("0");
        s4.setBorder(null);
        s4.setFocusable(false);
        jPanel105.add(s4, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel129.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/sides/4.jpg"))); // NOI18N
        jPanel105.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel106.setBackground(java.awt.Color.white);
        jPanel106.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel130.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel130.setForeground(new java.awt.Color(51, 51, 51));
        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel130.setText("Cheesy Mac & Cheese");
        jPanel106.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel131.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel131.setForeground(new java.awt.Color(0, 204, 255));
        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel131.setText("Php 75.00");
        jPanel106.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc39.setBorder(null);
        btnInc39.setFocusPainted(false);
        btnInc39.setFocusable(false);
        btnInc39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc39ActionPerformed(evt);
            }
        });
        jPanel106.add(btnInc39, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec39.setBorder(null);
        btnDec39.setFocusPainted(false);
        btnDec39.setFocusable(false);
        btnDec39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec39ActionPerformed(evt);
            }
        });
        jPanel106.add(btnDec39, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField39.setText("Quantity:");
        jTextField39.setBorder(null);
        jTextField39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField39ActionPerformed(evt);
            }
        });
        jPanel106.add(jTextField39, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        s3.setEditable(false);
        s3.setBackground(new java.awt.Color(255, 255, 255));
        s3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        s3.setForeground(new java.awt.Color(51, 153, 0));
        s3.setText("0");
        s3.setBorder(null);
        s3.setFocusable(false);
        s3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s3ActionPerformed(evt);
            }
        });
        jPanel106.add(s3, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel132.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/sides/3.jpg"))); // NOI18N
        jPanel106.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel107.setBackground(java.awt.Color.white);
        jPanel107.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel133.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel133.setForeground(new java.awt.Color(51, 51, 51));
        jLabel133.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel133.setText("Beef Bacon Overload");
        jPanel107.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 203, -1));

        jLabel134.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel134.setForeground(new java.awt.Color(0, 204, 255));
        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel134.setText("Php 75.00");
        jPanel107.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 203, -1));

        btnInc40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc40.setBorder(null);
        btnInc40.setFocusPainted(false);
        btnInc40.setFocusable(false);
        btnInc40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc40ActionPerformed(evt);
            }
        });
        jPanel107.add(btnInc40, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 177, 35, 33));

        btnDec40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec40.setBorder(null);
        btnDec40.setFocusPainted(false);
        btnDec40.setFocusable(false);
        btnDec40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec40ActionPerformed(evt);
            }
        });
        jPanel107.add(btnDec40, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 177, 35, 33));

        jTextField40.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField40.setText("Quantity:");
        jTextField40.setBorder(null);
        jTextField40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField40ActionPerformed(evt);
            }
        });
        jPanel107.add(jTextField40, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        s2.setEditable(false);
        s2.setBackground(new java.awt.Color(255, 255, 255));
        s2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        s2.setForeground(new java.awt.Color(51, 153, 0));
        s2.setText("0");
        s2.setBorder(null);
        s2.setFocusable(false);
        jPanel107.add(s2, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));
        jPanel107.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 0, -1, 125));

        jLabel136.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/sides/2.jpg"))); // NOI18N
        jPanel107.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel108.setBackground(java.awt.Color.white);
        jPanel108.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblSp7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlblSp7.setForeground(new java.awt.Color(51, 51, 51));
        jlblSp7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblSp7.setText("Baconet");
        jPanel108.add(jlblSp7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel137.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel137.setForeground(new java.awt.Color(0, 204, 255));
        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel137.setText("Php 75.00");
        jPanel108.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc41.setBorder(null);
        btnInc41.setFocusPainted(false);
        btnInc41.setFocusable(false);
        btnInc41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc41ActionPerformed(evt);
            }
        });
        jPanel108.add(btnInc41, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec41.setBorder(null);
        btnDec41.setFocusPainted(false);
        btnDec41.setFocusable(false);
        btnDec41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec41ActionPerformed(evt);
            }
        });
        jPanel108.add(btnDec41, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField41.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField41.setText("Quantity:");
        jTextField41.setBorder(null);
        jTextField41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField41ActionPerformed(evt);
            }
        });
        jPanel108.add(jTextField41, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        s1.setEditable(false);
        s1.setBackground(new java.awt.Color(255, 255, 255));
        s1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        s1.setForeground(new java.awt.Color(51, 153, 0));
        s1.setText("0");
        s1.setBorder(null);
        s1.setFocusable(false);
        s1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s1ActionPerformed(evt);
            }
        });
        jPanel108.add(s1, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel138.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/sides/1.jpg"))); // NOI18N
        jPanel108.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));
        jPanel108.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, 189, 125));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jPanel108, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel107, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel106, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jPanel105, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel104, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel103, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel106, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel107, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel108, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel103, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel104, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel105, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(191, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab8", jPanel17);

        jPanel74.setBackground(java.awt.Color.white);
        jPanel74.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Iced Coffee");
        jPanel74.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 204, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Php 65.00");
        jPanel74.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc7.setBorder(null);
        btnInc7.setFocusPainted(false);
        btnInc7.setFocusable(false);
        btnInc7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc7ActionPerformed(evt);
            }
        });
        jPanel74.add(btnInc7, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec7.setBorder(null);
        btnDec7.setFocusPainted(false);
        btnDec7.setFocusable(false);
        btnDec7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec7ActionPerformed(evt);
            }
        });
        jPanel74.add(btnDec7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField12.setText("Quantity:");
        jTextField12.setBorder(null);
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });
        jPanel74.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        dr6.setEditable(false);
        dr6.setBackground(new java.awt.Color(255, 255, 255));
        dr6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dr6.setForeground(new java.awt.Color(51, 153, 0));
        dr6.setText("0");
        dr6.setBorder(null);
        dr6.setFocusable(false);
        jPanel74.add(dr6, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/drinks/6.jpg"))); // NOI18N
        jPanel74.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel80.setBackground(java.awt.Color.white);
        jPanel80.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(51, 51, 51));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Lemonade");
        jPanel80.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 204, 255));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("Php 65.00");
        jPanel80.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc13.setBorder(null);
        btnInc13.setFocusPainted(false);
        btnInc13.setFocusable(false);
        btnInc13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc13ActionPerformed(evt);
            }
        });
        jPanel80.add(btnInc13, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec13.setBorder(null);
        btnDec13.setFocusPainted(false);
        btnDec13.setFocusable(false);
        btnDec13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec13ActionPerformed(evt);
            }
        });
        jPanel80.add(btnDec13, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField13.setText("Quantity:");
        jTextField13.setBorder(null);
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        jPanel80.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        dr5.setEditable(false);
        dr5.setBackground(new java.awt.Color(255, 255, 255));
        dr5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dr5.setForeground(new java.awt.Color(51, 153, 0));
        dr5.setText("0");
        dr5.setBorder(null);
        dr5.setFocusable(false);
        dr5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dr5ActionPerformed(evt);
            }
        });
        jPanel80.add(dr5, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/drinks/5.jpg"))); // NOI18N
        jPanel80.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel81.setBackground(java.awt.Color.white);
        jPanel81.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(51, 51, 51));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Macha Milk-tea");
        jPanel81.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 204, 255));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Php 65.00");
        jPanel81.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc14.setBorder(null);
        btnInc14.setFocusPainted(false);
        btnInc14.setFocusable(false);
        btnInc14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc14ActionPerformed(evt);
            }
        });
        jPanel81.add(btnInc14, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec14.setBorder(null);
        btnDec14.setFocusPainted(false);
        btnDec14.setFocusable(false);
        btnDec14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec14ActionPerformed(evt);
            }
        });
        jPanel81.add(btnDec14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField14.setText("Quantity:");
        jTextField14.setBorder(null);
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jPanel81.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        dr4.setEditable(false);
        dr4.setBackground(new java.awt.Color(255, 255, 255));
        dr4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dr4.setForeground(new java.awt.Color(51, 153, 0));
        dr4.setText("0");
        dr4.setBorder(null);
        dr4.setFocusable(false);
        jPanel81.add(dr4, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/drinks/4.jpg"))); // NOI18N
        jPanel81.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel82.setBackground(java.awt.Color.white);
        jPanel82.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(51, 51, 51));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("Strawberry Fruit Juice");
        jPanel82.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(0, 204, 255));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("Php 275.00");
        jPanel82.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc15.setBorder(null);
        btnInc15.setFocusPainted(false);
        btnInc15.setFocusable(false);
        btnInc15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc15ActionPerformed(evt);
            }
        });
        jPanel82.add(btnInc15, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec15.setBorder(null);
        btnDec15.setFocusPainted(false);
        btnDec15.setFocusable(false);
        btnDec15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec15ActionPerformed(evt);
            }
        });
        jPanel82.add(btnDec15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField15.setText("Quantity:");
        jTextField15.setBorder(null);
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });
        jPanel82.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        dr3.setEditable(false);
        dr3.setBackground(new java.awt.Color(255, 255, 255));
        dr3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dr3.setForeground(new java.awt.Color(51, 153, 0));
        dr3.setText("0");
        dr3.setBorder(null);
        dr3.setFocusable(false);
        dr3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dr3ActionPerformed(evt);
            }
        });
        jPanel82.add(dr3, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/drinks/3.jpg"))); // NOI18N
        jPanel82.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel83.setBackground(java.awt.Color.white);
        jPanel83.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(51, 51, 51));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Tiger Milk-tea");
        jPanel83.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 203, -1));

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 204, 255));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("Php 65.00");
        jPanel83.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 203, -1));

        btnInc16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc16.setBorder(null);
        btnInc16.setFocusPainted(false);
        btnInc16.setFocusable(false);
        btnInc16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc16ActionPerformed(evt);
            }
        });
        jPanel83.add(btnInc16, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 177, 35, 33));

        btnDec16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec16.setBorder(null);
        btnDec16.setFocusPainted(false);
        btnDec16.setFocusable(false);
        btnDec16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec16ActionPerformed(evt);
            }
        });
        jPanel83.add(btnDec16, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 177, 35, 33));

        jTextField16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField16.setText("Quantity:");
        jTextField16.setBorder(null);
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });
        jPanel83.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        dr2.setEditable(false);
        dr2.setBackground(new java.awt.Color(255, 255, 255));
        dr2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dr2.setForeground(new java.awt.Color(51, 153, 0));
        dr2.setText("0");
        dr2.setBorder(null);
        dr2.setFocusable(false);
        jPanel83.add(dr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));
        jPanel83.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 0, -1, 125));

        jLabel60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/drinks/2.jpg"))); // NOI18N
        jPanel83.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));

        jPanel84.setBackground(java.awt.Color.white);
        jPanel84.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblSp3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlblSp3.setForeground(new java.awt.Color(51, 51, 51));
        jlblSp3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblSp3.setText("Ube Milk-tea");
        jPanel84.add(jlblSp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 201, -1));

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 204, 255));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("Php 65.00");
        jPanel84.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 201, -1));

        btnInc17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Paomedia-Small-N-Flat-Sign-add.24.png"))); // NOI18N
        btnInc17.setBorder(null);
        btnInc17.setFocusPainted(false);
        btnInc17.setFocusable(false);
        btnInc17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc17ActionPerformed(evt);
            }
        });
        jPanel84.add(btnInc17, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 177, 35, 33));

        btnDec17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/Gakuseisean-Ivista-Minus.24.png"))); // NOI18N
        btnDec17.setBorder(null);
        btnDec17.setFocusPainted(false);
        btnDec17.setFocusable(false);
        btnDec17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDec17ActionPerformed(evt);
            }
        });
        jPanel84.add(btnDec17, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 177, 35, 33));

        jTextField17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField17.setText("Quantity:");
        jTextField17.setBorder(null);
        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });
        jPanel84.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 53, 33));

        dr1.setEditable(false);
        dr1.setBackground(new java.awt.Color(255, 255, 255));
        dr1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dr1.setForeground(new java.awt.Color(51, 153, 0));
        dr1.setText("0");
        dr1.setBorder(null);
        dr1.setFocusable(false);
        dr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dr1ActionPerformed(evt);
            }
        });
        jPanel84.add(dr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 177, 44, 33));

        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product-images/drinks/1.jpg"))); // NOI18N
        jPanel84.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 201, 125));
        jPanel84.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, 189, 125));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jPanel84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel83, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jPanel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(191, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab9", jPanel19);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, -50, 640, 720));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChckBurgerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChckBurgerActionPerformed
        jTabbedPane1.setSelectedIndex(1); // used to switch tabs
        //reset color to white
        //to-do: DRY principle dito
        sidebarHighlight(btnChckBurger);             
    }//GEN-LAST:event_btnChckBurgerActionPerformed

    private void btnSpBurgerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpBurgerActionPerformed
        jTabbedPane1.setSelectedIndex(0);
        sidebarHighlight(btnSpBurger);
    }//GEN-LAST:event_btnSpBurgerActionPerformed

    private void btnBurgerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBurgerActionPerformed
        jTabbedPane1.setSelectedIndex(2);
        sidebarHighlight(btnBurger);   
    }//GEN-LAST:event_btnBurgerActionPerformed

    private void btnMiniBurgerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMiniBurgerActionPerformed
        jTabbedPane1.setSelectedIndex(3);
        sidebarHighlight(btnMiniBurger);  
    }//GEN-LAST:event_btnMiniBurgerActionPerformed

    private void btnHotdogsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHotdogsActionPerformed
        jTabbedPane1.setSelectedIndex(4);
        sidebarHighlight(btnHotdogs);
    }//GEN-LAST:event_btnHotdogsActionPerformed

    private void btnGrillsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrillsActionPerformed
        jTabbedPane1.setSelectedIndex(5);
        sidebarHighlight(btnGrills);
    }//GEN-LAST:event_btnGrillsActionPerformed

    private void btnDessertsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDessertsActionPerformed
        jTabbedPane1.setSelectedIndex(6);
        sidebarHighlight(btnDesserts);
    }//GEN-LAST:event_btnDessertsActionPerformed

    private void btnSidesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSidesActionPerformed
        jTabbedPane1.setSelectedIndex(7);
        sidebarHighlight(btnSides);
    }//GEN-LAST:event_btnSidesActionPerformed

    private void btnDrinksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrinksActionPerformed
        jTabbedPane1.setSelectedIndex(8);
        sidebarHighlight(btnDrinks);
    }//GEN-LAST:event_btnDrinksActionPerformed

    private void btnOrderingViewExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderingViewExitActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Ordering View", JOptionPane.YES_NO_OPTION);
        
        if(result == JOptionPane.YES_OPTION){
            new MainMenu().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnOrderingViewExitActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jtxtQty15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtQty15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtQty15ActionPerformed

    private void jtxtSubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtSubtotalActionPerformed

    }//GEN-LAST:event_jtxtSubtotalActionPerformed

    private void jtxtTAXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtTAXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtTAXActionPerformed

    private void jtxtDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtDiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtDiscountActionPerformed

    private void jtxtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtTotalActionPerformed

    private void btnInc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc1ActionPerformed
        incrementProductQuantity(SPburger1);
        String value = Integer.toString(SPburger1.getQuantity());
        jtxtQty11.setText(value);
    }//GEN-LAST:event_btnInc1ActionPerformed

    private void jtxtQty11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtQty11ActionPerformed
        
    }//GEN-LAST:event_jtxtQty11ActionPerformed

    private void btnDec1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec1ActionPerformed
        decrementProductQuantity(SPburger1);
        String value = Integer.toString(SPburger1.getQuantity());
        jtxtQty11.setText(value);
    }//GEN-LAST:event_btnDec1ActionPerformed

    private void jTableMyOrderInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTableMyOrderInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableMyOrderInputMethodTextChanged

    private void btnInc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc2ActionPerformed
        incrementProductQuantity(SPburger2);
        String value = Integer.toString(SPburger2.getQuantity());
        jtxtQty12.setText(value);
    }//GEN-LAST:event_btnInc2ActionPerformed

    private void jtxtQty13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtQty13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtQty13ActionPerformed

    private void btnDec2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec2ActionPerformed
       decrementProductQuantity(SPburger2);
       String value = Integer.toString(SPburger2.getQuantity());
       jtxtQty12.setText(value);
    }//GEN-LAST:event_btnDec2ActionPerformed

    private void btnInc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc3ActionPerformed
        incrementProductQuantity(SPburger3);
        String value = Integer.toString(SPburger3.getQuantity());
        jtxtQty13.setText(value);
    }//GEN-LAST:event_btnInc3ActionPerformed

    private void btnDec3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec3ActionPerformed
        decrementProductQuantity(SPburger3);
        String value = Integer.toString(SPburger3.getQuantity());
        jtxtQty13.setText(value);
    }//GEN-LAST:event_btnDec3ActionPerformed

    private void btnInc4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc4ActionPerformed
        incrementProductQuantity(SPburger4);
        String value = Integer.toString(SPburger4.getQuantity());
        jtxtQty14.setText(value);
    }//GEN-LAST:event_btnInc4ActionPerformed

    private void btnDec4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec4ActionPerformed
        decrementProductQuantity(SPburger4);
        String value = Integer.toString(SPburger4.getQuantity());
        jtxtQty14.setText(value);
    }//GEN-LAST:event_btnDec4ActionPerformed

    private void btnInc5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc5ActionPerformed
        incrementProductQuantity(SPburger5);
        String value = Integer.toString(SPburger5.getQuantity());
        jtxtQty15.setText(value);
    }//GEN-LAST:event_btnInc5ActionPerformed

    private void btnDec5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec5ActionPerformed
        decrementProductQuantity(SPburger5);
        String value = Integer.toString(SPburger5.getQuantity());
        jtxtQty15.setText(value);
    }//GEN-LAST:event_btnDec5ActionPerformed

    private void btnInc6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc6ActionPerformed
        incrementProductQuantity(SPburger6);
        String value = Integer.toString(SPburger6.getQuantity());
        jtxtQty16.setText(value);
    }//GEN-LAST:event_btnInc6ActionPerformed

    private void btnDec6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec6ActionPerformed
        decrementProductQuantity(SPburger6);
        String value = Integer.toString(SPburger6.getQuantity());
        jtxtQty16.setText(value);
    }//GEN-LAST:event_btnDec6ActionPerformed

    private void btnOrderListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderListActionPerformed
        // method call
        controller.getOrderedProducts();

        //updates product total cost
        jtxtSubtotal.setText(String.format("Php %.2f", controller.calculateSubtotal()));
        jtxtTAX.setText(String.format("Php %.2f", controller.calculateTax()));
        jtxtDiscount.setText(String.format("Php %.2f", controller.calculateDiscount()));
        jtxtTotal.setText(String.format("Php %.2f", controller.calculateTotal()));

        jtxtSubtotal.revalidate();
        jtxtSubtotal.repaint();

    }//GEN-LAST:event_btnOrderListActionPerformed

    private void btnPayNow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayNow1ActionPerformed
        // order confirmation
        if(controller.getOrderedProducts().isEmpty()){
            JOptionPane.showMessageDialog(null,"No selected order, please try again...");
        }
        else{
            int result = JOptionPane.showConfirmDialog(null, "Do you want to proceed to payment?", "Order Confirmation", JOptionPane.YES_NO_OPTION);

            if(result == JOptionPane.YES_OPTION){
                boolean success = controller.submitOrderToDatabase();
                    if(!success){
                        JOptionPane.showMessageDialog(null,"error submiting order to database",  "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "order succesfull");
                    }
            }
        }
    }//GEN-LAST:event_btnPayNow1ActionPerformed

    private void btnInc8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc8ActionPerformed
        incrementProductQuantity(Cburger5);
        String value = Integer.toString(Cburger5.getQuantity());
        cb5.setText(value);
    }//GEN-LAST:event_btnInc8ActionPerformed

    private void btnDec8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec8ActionPerformed
        decrementProductQuantity(Cburger5);
        String value = Integer.toString(Cburger5.getQuantity());
        cb5.setText(value);
    }//GEN-LAST:event_btnDec8ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void cb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb5ActionPerformed

    private void btnInc9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc9ActionPerformed
        incrementProductQuantity(Cburger4);
        String value = Integer.toString(Cburger4.getQuantity());
        cb4.setText(value);
    }//GEN-LAST:event_btnInc9ActionPerformed

    private void btnDec9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec9ActionPerformed
        decrementProductQuantity(Cburger4);
        String value = Integer.toString(Cburger4.getQuantity());
        cb4.setText(value);
    }//GEN-LAST:event_btnDec9ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void btnInc10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc10ActionPerformed
        incrementProductQuantity(Cburger3);
        String value = Integer.toString(Cburger3.getQuantity());
        cb3.setText(value);
    }//GEN-LAST:event_btnInc10ActionPerformed

    private void btnDec10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec10ActionPerformed
        decrementProductQuantity(Cburger3);
        String value = Integer.toString(Cburger3.getQuantity());
        cb3.setText(value);
    }//GEN-LAST:event_btnDec10ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void cb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb3ActionPerformed

    private void btnInc11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc11ActionPerformed
        incrementProductQuantity(Cburger2);
        String value = Integer.toString(Cburger2.getQuantity());
        cb2.setText(value);
    }//GEN-LAST:event_btnInc11ActionPerformed

    private void btnDec11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec11ActionPerformed
        decrementProductQuantity(Cburger2);
        String value = Integer.toString(Cburger2.getQuantity());
        cb2.setText(value);
    }//GEN-LAST:event_btnDec11ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void btnInc12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc12ActionPerformed
        incrementProductQuantity(Cburger1);
        String value = Integer.toString(Cburger1.getQuantity());
        cb1.setText(value);
    }//GEN-LAST:event_btnInc12ActionPerformed

    private void btnDec12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec12ActionPerformed
        decrementProductQuantity(Cburger1);
        String value = Integer.toString(Cburger1.getQuantity());
        cb1.setText(value);
    }//GEN-LAST:event_btnDec12ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        incrementProductQuantity(Cburger1);
        String value = Integer.toString(Cburger1.getQuantity());
        cb1.setText(value);
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void cb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb1ActionPerformed

    private void btnInc7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc7ActionPerformed
        incrementProductQuantity(Drink6);
        String value = Integer.toString(Drink6.getQuantity());
        dr6.setText(value);
    }//GEN-LAST:event_btnInc7ActionPerformed

    private void btnDec7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec7ActionPerformed
        decrementProductQuantity(Drink6);
        String value = Integer.toString(Drink6.getQuantity());
        dr6.setText(value);
    }//GEN-LAST:event_btnDec7ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void btnInc13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc13ActionPerformed
        incrementProductQuantity(Drink5);
        String value = Integer.toString(Drink5.getQuantity());
        dr5.setText(value);
    }//GEN-LAST:event_btnInc13ActionPerformed

    private void btnDec13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec13ActionPerformed
        decrementProductQuantity(Drink5);
        String value = Integer.toString(Drink5.getQuantity());
        dr5.setText(value);
    }//GEN-LAST:event_btnDec13ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void dr5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dr5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dr5ActionPerformed

    private void btnInc14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc14ActionPerformed
        incrementProductQuantity(Drink4);
        String value = Integer.toString(Drink4.getQuantity());
        dr4.setText(value);
    }//GEN-LAST:event_btnInc14ActionPerformed

    private void btnDec14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec14ActionPerformed
        decrementProductQuantity(Drink4);
        String value = Integer.toString(Drink4.getQuantity());
        dr4.setText(value);
    }//GEN-LAST:event_btnDec14ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void btnInc15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc15ActionPerformed
        incrementProductQuantity(Drink3);
        String value = Integer.toString(Drink3.getQuantity());
        dr3.setText(value);
    }//GEN-LAST:event_btnInc15ActionPerformed

    private void btnDec15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec15ActionPerformed
        decrementProductQuantity(Drink3);
        String value = Integer.toString(Drink3.getQuantity());
        dr3.setText(value);
    }//GEN-LAST:event_btnDec15ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void dr3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dr3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dr3ActionPerformed

    private void btnInc16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc16ActionPerformed
        incrementProductQuantity(Drink2);
        String value = Integer.toString(Drink2.getQuantity());
        dr2.setText(value);
    }//GEN-LAST:event_btnInc16ActionPerformed

    private void btnDec16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec16ActionPerformed
        decrementProductQuantity(Drink2);
        String value = Integer.toString(Drink2.getQuantity());
        dr2.setText(value);
    }//GEN-LAST:event_btnDec16ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void btnInc17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc17ActionPerformed
        incrementProductQuantity(Drink1);
        String value = Integer.toString(Drink1.getQuantity());
        dr1.setText(value);
    }//GEN-LAST:event_btnInc17ActionPerformed

    private void btnDec17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec17ActionPerformed
        decrementProductQuantity(Drink1);
        String value = Integer.toString(Drink1.getQuantity());
        dr1.setText(value);
    }//GEN-LAST:event_btnDec17ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void dr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dr1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dr1ActionPerformed

    private void btnInc18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc18ActionPerformed
        incrementProductQuantity(Mburger6);
        String value = Integer.toString(Mburger6.getQuantity());
        mb6.setText(value);
    }//GEN-LAST:event_btnInc18ActionPerformed

    private void btnDec18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDec18ActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void btnInc19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc19ActionPerformed
        incrementProductQuantity(Mburger5);
        String value = Integer.toString(Mburger5.getQuantity());
        mb5.setText(value);
    }//GEN-LAST:event_btnInc19ActionPerformed

    private void btnDec19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec19ActionPerformed
        decrementProductQuantity(Mburger5);
        String value = Integer.toString(Mburger5.getQuantity());
        mb5.setText(value);
    }//GEN-LAST:event_btnDec19ActionPerformed

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed

    private void mb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mb5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mb5ActionPerformed

    private void btnInc20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc20ActionPerformed
        incrementProductQuantity(Mburger4);
        String value = Integer.toString(Mburger4.getQuantity());
        mb4.setText(value);
    }//GEN-LAST:event_btnInc20ActionPerformed

    private void btnDec20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec20ActionPerformed
        decrementProductQuantity(Mburger4);
        String value = Integer.toString(Mburger4.getQuantity());
        mb4.setText(value);
    }//GEN-LAST:event_btnDec20ActionPerformed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

    private void btnInc21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc21ActionPerformed
        incrementProductQuantity(Mburger3);
        String value = Integer.toString(Mburger3.getQuantity());
        mb3.setText(value);
    }//GEN-LAST:event_btnInc21ActionPerformed

    private void btnDec21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec21ActionPerformed
        decrementProductQuantity(Mburger3);
        String value = Integer.toString(Mburger3.getQuantity());
        mb3.setText(value);
    }//GEN-LAST:event_btnDec21ActionPerformed

    private void jTextField21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField21ActionPerformed

    private void mb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mb3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mb3ActionPerformed

    private void btnInc22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc22ActionPerformed
        incrementProductQuantity(Mburger2);
        String value = Integer.toString(Mburger2.getQuantity());
        mb2.setText(value);
    }//GEN-LAST:event_btnInc22ActionPerformed

    private void btnDec22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec22ActionPerformed
        decrementProductQuantity(Mburger2);
        String value = Integer.toString(Mburger2.getQuantity());
        mb2.setText(value);
    }//GEN-LAST:event_btnDec22ActionPerformed

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField22ActionPerformed

    private void btnInc23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc23ActionPerformed
        incrementProductQuantity(Mburger1);
        String value = Integer.toString(Mburger1.getQuantity());
        mb1.setText(value);
    }//GEN-LAST:event_btnInc23ActionPerformed

    private void btnDec23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec23ActionPerformed
        decrementProductQuantity(Mburger2);
        String value = Integer.toString(Mburger2.getQuantity());
        mb2.setText(value);
    }//GEN-LAST:event_btnDec23ActionPerformed

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void mb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mb1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mb1ActionPerformed

    private void btnInc24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc24ActionPerformed
        incrementProductQuantity(Hotdog6);
        String value = Integer.toString(Hotdog6.getQuantity());
        h6.setText(value);
    }//GEN-LAST:event_btnInc24ActionPerformed

    private void btnDec24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDec24ActionPerformed

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField24ActionPerformed

    private void btnInc25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc25ActionPerformed
        incrementProductQuantity(Hotdog5);
        String value = Integer.toString(Hotdog5.getQuantity());
        h5.setText(value);
    }//GEN-LAST:event_btnInc25ActionPerformed

    private void btnDec25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec25ActionPerformed
        decrementProductQuantity(Hotdog5);
        String value = Integer.toString(Hotdog5.getQuantity());
        h5.setText(value);
    }//GEN-LAST:event_btnDec25ActionPerformed

    private void jTextField25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField25ActionPerformed

    private void h5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_h5ActionPerformed

    private void btnInc26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc26ActionPerformed
        incrementProductQuantity(Hotdog4);
        String value = Integer.toString(Hotdog4.getQuantity());
        h4.setText(value);
    }//GEN-LAST:event_btnInc26ActionPerformed

    private void btnDec26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec26ActionPerformed
        decrementProductQuantity(Hotdog4);
        String value = Integer.toString(Hotdog4.getQuantity());
        h4.setText(value);
    }//GEN-LAST:event_btnDec26ActionPerformed

    private void jTextField26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField26ActionPerformed

    private void btnInc27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc27ActionPerformed
        incrementProductQuantity(Hotdog3);
        String value = Integer.toString(Hotdog3.getQuantity());
        h3.setText(value);
    }//GEN-LAST:event_btnInc27ActionPerformed

    private void btnDec27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec27ActionPerformed
        decrementProductQuantity(Hotdog3);
        String value = Integer.toString(Hotdog3.getQuantity());
        h3.setText(value);
    }//GEN-LAST:event_btnDec27ActionPerformed

    private void jTextField27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField27ActionPerformed

    private void h3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_h3ActionPerformed

    private void btnInc28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc28ActionPerformed
        incrementProductQuantity(Hotdog2);
        String value = Integer.toString(Hotdog2.getQuantity());
        h2.setText(value);
    }//GEN-LAST:event_btnInc28ActionPerformed

    private void btnDec28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec28ActionPerformed
        decrementProductQuantity(Hotdog2);
        String value = Integer.toString(Hotdog2.getQuantity());
        h2.setText(value);
    }//GEN-LAST:event_btnDec28ActionPerformed

    private void jTextField28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField28ActionPerformed

    private void btnInc29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc29ActionPerformed
        incrementProductQuantity(Hotdog1);
        String value = Integer.toString(Hotdog1.getQuantity());
        h1.setText(value);
    }//GEN-LAST:event_btnInc29ActionPerformed

    private void btnDec29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec29ActionPerformed
        decrementProductQuantity(Hotdog1);
        String value = Integer.toString(Hotdog1.getQuantity());
        h1.setText(value);
    }//GEN-LAST:event_btnDec29ActionPerformed

    private void jTextField29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField29ActionPerformed

    private void h1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_h1ActionPerformed

    private void btnInc30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc30ActionPerformed
        incrementProductQuantity(Dessert6);
        String value = Integer.toString(Dessert6.getQuantity());
        d6.setText(value);
    }//GEN-LAST:event_btnInc30ActionPerformed

    private void btnDec30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec30ActionPerformed
        decrementProductQuantity(Dessert6);
        String value = Integer.toString(Dessert6.getQuantity());
        d6.setText(value);
    }//GEN-LAST:event_btnDec30ActionPerformed

    private void jTextField30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField30ActionPerformed

    private void btnInc31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc31ActionPerformed
        incrementProductQuantity(Dessert5);
        String value = Integer.toString(Dessert5.getQuantity());
        d5.setText(value);
    }//GEN-LAST:event_btnInc31ActionPerformed

    private void btnDec31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec31ActionPerformed
        decrementProductQuantity(Dessert5);
        String value = Integer.toString(Dessert5.getQuantity());
        d5.setText(value);
    }//GEN-LAST:event_btnDec31ActionPerformed

    private void jTextField31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31ActionPerformed

    private void d5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_d5ActionPerformed

    private void btnInc32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc32ActionPerformed
        incrementProductQuantity(Dessert4);
        String value = Integer.toString(Dessert4.getQuantity());
        d4.setText(value);
    }//GEN-LAST:event_btnInc32ActionPerformed

    private void btnDec32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec32ActionPerformed
        decrementProductQuantity(Dessert4);
        String value = Integer.toString(Dessert4.getQuantity());
        d4.setText(value);
    }//GEN-LAST:event_btnDec32ActionPerformed

    private void jTextField32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField32ActionPerformed

    private void btnInc33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc33ActionPerformed
        incrementProductQuantity(Dessert3);
        String value = Integer.toString(Dessert3.getQuantity());
        d3.setText(value);
    }//GEN-LAST:event_btnInc33ActionPerformed

    private void btnDec33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec33ActionPerformed
        decrementProductQuantity(Dessert3);
        String value = Integer.toString(Dessert3.getQuantity());
        d3.setText(value);
    }//GEN-LAST:event_btnDec33ActionPerformed

    private void jTextField33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField33ActionPerformed

    private void d3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_d3ActionPerformed

    private void btnInc34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc34ActionPerformed
        incrementProductQuantity(Dessert2);
        String value = Integer.toString(Dessert2.getQuantity());
        d2.setText(value);
    }//GEN-LAST:event_btnInc34ActionPerformed

    private void btnDec34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec34ActionPerformed
        decrementProductQuantity(Dessert2);
        String value = Integer.toString(Dessert2.getQuantity());
        d2.setText(value);
    }//GEN-LAST:event_btnDec34ActionPerformed

    private void jTextField34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField34ActionPerformed

    private void btnInc35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc35ActionPerformed
        incrementProductQuantity(Dessert1);
        String value = Integer.toString(Dessert1.getQuantity());
        d1.setText(value);
    }//GEN-LAST:event_btnInc35ActionPerformed

    private void btnDec35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec35ActionPerformed
        decrementProductQuantity(Dessert1);
        String value = Integer.toString(Dessert1.getQuantity());
        d1.setText(value);
    }//GEN-LAST:event_btnDec35ActionPerformed

    private void jTextField35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField35ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField35ActionPerformed

    private void d1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_d1ActionPerformed

    private void btnInc36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc36ActionPerformed
        incrementProductQuantity(Side6);
        String value = Integer.toString(Side6.getQuantity());
        s6.setText(value);
    }//GEN-LAST:event_btnInc36ActionPerformed

    private void btnDec36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec36ActionPerformed
        decrementProductQuantity(Side6);
        String value = Integer.toString(Side6.getQuantity());
        s6.setText(value);
    }//GEN-LAST:event_btnDec36ActionPerformed

    private void jTextField36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField36ActionPerformed

    private void btnInc37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc37ActionPerformed
        incrementProductQuantity(Side5);
        String value = Integer.toString(Side5.getQuantity());
        s5.setText(value);
    }//GEN-LAST:event_btnInc37ActionPerformed

    private void btnDec37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec37ActionPerformed
        decrementProductQuantity(Side5);
        String value = Integer.toString(Side5.getQuantity());
        s5.setText(value);
    }//GEN-LAST:event_btnDec37ActionPerformed

    private void jTextField37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField37ActionPerformed

    private void s5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s5ActionPerformed

    private void btnInc38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc38ActionPerformed
        incrementProductQuantity(Side4);
        String value = Integer.toString(Side4.getQuantity());
        s4.setText(value);
    }//GEN-LAST:event_btnInc38ActionPerformed

    private void btnDec38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec38ActionPerformed
        decrementProductQuantity(Side4);
        String value = Integer.toString(Side4.getQuantity());
        s4.setText(value);
    }//GEN-LAST:event_btnDec38ActionPerformed

    private void jTextField38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField38ActionPerformed

    private void btnInc39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc39ActionPerformed
        incrementProductQuantity(Side3);
        String value = Integer.toString(Side3.getQuantity());
        s3.setText(value);
    }//GEN-LAST:event_btnInc39ActionPerformed

    private void btnDec39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec39ActionPerformed
        decrementProductQuantity(Side3);
        String value = Integer.toString(Side3.getQuantity());
        s3.setText(value);
    }//GEN-LAST:event_btnDec39ActionPerformed

    private void jTextField39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField39ActionPerformed

    private void s3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s3ActionPerformed

    private void btnInc40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc40ActionPerformed
        incrementProductQuantity(Side2);
        String value = Integer.toString(Side2.getQuantity());
        s2.setText(value);
    }//GEN-LAST:event_btnInc40ActionPerformed

    private void btnDec40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec40ActionPerformed
        decrementProductQuantity(Side2);
        String value = Integer.toString(Side2.getQuantity());
        s2.setText(value);
    }//GEN-LAST:event_btnDec40ActionPerformed

    private void jTextField40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField40ActionPerformed

    private void btnInc41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc41ActionPerformed
        incrementProductQuantity(Side1);
        String value = Integer.toString(Side1.getQuantity());
        s1.setText(value);
    }//GEN-LAST:event_btnInc41ActionPerformed

    private void btnDec41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec41ActionPerformed
        decrementProductQuantity(Side1);
        String value = Integer.toString(Side1.getQuantity());
        s1.setText(value);
    }//GEN-LAST:event_btnDec41ActionPerformed

    private void jTextField41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField41ActionPerformed

    private void s1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s1ActionPerformed

    private void btnInc42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc42ActionPerformed
        decrementProductQuantity(Burger6);
        String value = Integer.toString(Burger6.getQuantity());
        b6.setText(value);
    }//GEN-LAST:event_btnInc42ActionPerformed

    private void btnDec42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDec42ActionPerformed

    private void jTextField42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField42ActionPerformed

    private void btnInc43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc43ActionPerformed
        incrementProductQuantity(Burger5);
        String value = Integer.toString(Burger5.getQuantity());
        b5.setText(value);
    }//GEN-LAST:event_btnInc43ActionPerformed

    private void btnDec43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec43ActionPerformed
        decrementProductQuantity(Burger5);
        String value = Integer.toString(Burger5.getQuantity());
        b5.setText(value);
    }//GEN-LAST:event_btnDec43ActionPerformed

    private void jTextField43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField43ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField43ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b5ActionPerformed

    private void btnInc44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc44ActionPerformed
        incrementProductQuantity(Burger4);
        String value = Integer.toString(Burger4.getQuantity());
        b4.setText(value);
    }//GEN-LAST:event_btnInc44ActionPerformed

    private void btnDec44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec44ActionPerformed
        decrementProductQuantity(Burger4);
        String value = Integer.toString(Burger4.getQuantity());
        b4.setText(value);
    }//GEN-LAST:event_btnDec44ActionPerformed

    private void jTextField44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField44ActionPerformed

    private void btnInc45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc45ActionPerformed
        incrementProductQuantity(Burger3);
        String value = Integer.toString(Burger3.getQuantity());
        b3.setText(value);
    }//GEN-LAST:event_btnInc45ActionPerformed

    private void btnDec45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec45ActionPerformed
        decrementProductQuantity(Burger3);
        String value = Integer.toString(Burger3.getQuantity());
        b3.setText(value);
    }//GEN-LAST:event_btnDec45ActionPerformed

    private void jTextField45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField45ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField45ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b3ActionPerformed

    private void btnInc46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc46ActionPerformed
        incrementProductQuantity(Burger2);
        String value = Integer.toString(Burger2.getQuantity());
        b2.setText(value);
    }//GEN-LAST:event_btnInc46ActionPerformed

    private void btnDec46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec46ActionPerformed
        decrementProductQuantity(Burger2);
        String value = Integer.toString(Burger2.getQuantity());
        b2.setText(value);
    }//GEN-LAST:event_btnDec46ActionPerformed

    private void jTextField46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField46ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField46ActionPerformed

    private void btnInc47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc47ActionPerformed
        incrementProductQuantity(Burger1);
        String value = Integer.toString(Burger1.getQuantity());
        b1.setText(value);
    }//GEN-LAST:event_btnInc47ActionPerformed

    private void btnDec47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec47ActionPerformed
        decrementProductQuantity(Burger1);
        String value = Integer.toString(Burger1.getQuantity());
        b1.setText(value);
    }//GEN-LAST:event_btnDec47ActionPerformed

    private void jTextField47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField47ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField47ActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b1ActionPerformed

    private void btnInc48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc48ActionPerformed
        incrementProductQuantity(Grill6);
        String value = Integer.toString(Grill6.getQuantity());
        g6.setText(value);
    }//GEN-LAST:event_btnInc48ActionPerformed

    private void btnDec48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec48ActionPerformed
        decrementProductQuantity(Grill6);
        String value = Integer.toString(Grill6.getQuantity());
        g6.setText(value);
    }//GEN-LAST:event_btnDec48ActionPerformed

    private void jTextField48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField48ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField48ActionPerformed

    private void btnInc49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc49ActionPerformed
        incrementProductQuantity(Grill5);
        String value = Integer.toString(Grill5.getQuantity());
        g5.setText(value);
    }//GEN-LAST:event_btnInc49ActionPerformed

    private void btnDec49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec49ActionPerformed
        decrementProductQuantity(Grill5);
        String value = Integer.toString(Grill5.getQuantity());
        g5.setText(value);
    }//GEN-LAST:event_btnDec49ActionPerformed

    private void jTextField49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField49ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField49ActionPerformed

    private void g5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_g5ActionPerformed

    private void btnInc50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc50ActionPerformed
        incrementProductQuantity(Grill4);
        String value = Integer.toString(Grill4.getQuantity());
        g4.setText(value);
    }//GEN-LAST:event_btnInc50ActionPerformed

    private void btnDec50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec50ActionPerformed
        decrementProductQuantity(Grill4);
        String value = Integer.toString(Grill4.getQuantity());
        g4.setText(value);
    }//GEN-LAST:event_btnDec50ActionPerformed

    private void jTextField50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField50ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField50ActionPerformed

    private void btnInc51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc51ActionPerformed
        incrementProductQuantity(Grill3);
        String value = Integer.toString(Grill3.getQuantity());
        g3.setText(value);
    }//GEN-LAST:event_btnInc51ActionPerformed

    private void btnDec51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec51ActionPerformed
        decrementProductQuantity(Grill3);
        String value = Integer.toString(Grill3.getQuantity());
        g3.setText(value);
    }//GEN-LAST:event_btnDec51ActionPerformed

    private void jTextField51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField51ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField51ActionPerformed

    private void g3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_g3ActionPerformed

    private void btnInc52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc52ActionPerformed
        incrementProductQuantity(Grill2);
        String value = Integer.toString(Grill2.getQuantity());
        g2.setText(value);
    }//GEN-LAST:event_btnInc52ActionPerformed

    private void btnDec52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec52ActionPerformed
        decrementProductQuantity(Grill2);
        String value = Integer.toString(Grill2.getQuantity());
        g2.setText(value);
    }//GEN-LAST:event_btnDec52ActionPerformed

    private void jTextField52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField52ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField52ActionPerformed

    private void btnInc53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc53ActionPerformed
        incrementProductQuantity(Grill1);
        String value = Integer.toString(Grill1.getQuantity());
        g1.setText(value);
    }//GEN-LAST:event_btnInc53ActionPerformed

    private void btnDec53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDec53ActionPerformed
        decrementProductQuantity(Grill1);
        String value = Integer.toString(Grill1.getQuantity());
        g1.setText(value);
    }//GEN-LAST:event_btnDec53ActionPerformed

    private void jTextField53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField53ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField53ActionPerformed

    private void g1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_g1ActionPerformed

    private void cb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb2ActionPerformed

    private void d2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_d2ActionPerformed

    public static void main(String args[]) {

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderingView().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField b1;
    private javax.swing.JTextField b2;
    private javax.swing.JTextField b3;
    private javax.swing.JTextField b4;
    private javax.swing.JTextField b5;
    private javax.swing.JTextField b6;
    private javax.swing.JButton btnBurger;
    private javax.swing.JButton btnChckBurger;
    private javax.swing.JButton btnDec1;
    private javax.swing.JButton btnDec10;
    private javax.swing.JButton btnDec11;
    private javax.swing.JButton btnDec12;
    private javax.swing.JButton btnDec13;
    private javax.swing.JButton btnDec14;
    private javax.swing.JButton btnDec15;
    private javax.swing.JButton btnDec16;
    private javax.swing.JButton btnDec17;
    private javax.swing.JButton btnDec18;
    private javax.swing.JButton btnDec19;
    private javax.swing.JButton btnDec2;
    private javax.swing.JButton btnDec20;
    private javax.swing.JButton btnDec21;
    private javax.swing.JButton btnDec22;
    private javax.swing.JButton btnDec23;
    private javax.swing.JButton btnDec24;
    private javax.swing.JButton btnDec25;
    private javax.swing.JButton btnDec26;
    private javax.swing.JButton btnDec27;
    private javax.swing.JButton btnDec28;
    private javax.swing.JButton btnDec29;
    private javax.swing.JButton btnDec3;
    private javax.swing.JButton btnDec30;
    private javax.swing.JButton btnDec31;
    private javax.swing.JButton btnDec32;
    private javax.swing.JButton btnDec33;
    private javax.swing.JButton btnDec34;
    private javax.swing.JButton btnDec35;
    private javax.swing.JButton btnDec36;
    private javax.swing.JButton btnDec37;
    private javax.swing.JButton btnDec38;
    private javax.swing.JButton btnDec39;
    private javax.swing.JButton btnDec4;
    private javax.swing.JButton btnDec40;
    private javax.swing.JButton btnDec41;
    private javax.swing.JButton btnDec42;
    private javax.swing.JButton btnDec43;
    private javax.swing.JButton btnDec44;
    private javax.swing.JButton btnDec45;
    private javax.swing.JButton btnDec46;
    private javax.swing.JButton btnDec47;
    private javax.swing.JButton btnDec48;
    private javax.swing.JButton btnDec49;
    private javax.swing.JButton btnDec5;
    private javax.swing.JButton btnDec50;
    private javax.swing.JButton btnDec51;
    private javax.swing.JButton btnDec52;
    private javax.swing.JButton btnDec53;
    private javax.swing.JButton btnDec6;
    private javax.swing.JButton btnDec7;
    private javax.swing.JButton btnDec8;
    private javax.swing.JButton btnDec9;
    private javax.swing.JButton btnDesserts;
    private javax.swing.JButton btnDrinks;
    private javax.swing.JButton btnGrills;
    private javax.swing.JButton btnHotdogs;
    private javax.swing.JButton btnInc1;
    private javax.swing.JButton btnInc10;
    private javax.swing.JButton btnInc11;
    private javax.swing.JButton btnInc12;
    private javax.swing.JButton btnInc13;
    private javax.swing.JButton btnInc14;
    private javax.swing.JButton btnInc15;
    private javax.swing.JButton btnInc16;
    private javax.swing.JButton btnInc17;
    private javax.swing.JButton btnInc18;
    private javax.swing.JButton btnInc19;
    private javax.swing.JButton btnInc2;
    private javax.swing.JButton btnInc20;
    private javax.swing.JButton btnInc21;
    private javax.swing.JButton btnInc22;
    private javax.swing.JButton btnInc23;
    private javax.swing.JButton btnInc24;
    private javax.swing.JButton btnInc25;
    private javax.swing.JButton btnInc26;
    private javax.swing.JButton btnInc27;
    private javax.swing.JButton btnInc28;
    private javax.swing.JButton btnInc29;
    private javax.swing.JButton btnInc3;
    private javax.swing.JButton btnInc30;
    private javax.swing.JButton btnInc31;
    private javax.swing.JButton btnInc32;
    private javax.swing.JButton btnInc33;
    private javax.swing.JButton btnInc34;
    private javax.swing.JButton btnInc35;
    private javax.swing.JButton btnInc36;
    private javax.swing.JButton btnInc37;
    private javax.swing.JButton btnInc38;
    private javax.swing.JButton btnInc39;
    private javax.swing.JButton btnInc4;
    private javax.swing.JButton btnInc40;
    private javax.swing.JButton btnInc41;
    private javax.swing.JButton btnInc42;
    private javax.swing.JButton btnInc43;
    private javax.swing.JButton btnInc44;
    private javax.swing.JButton btnInc45;
    private javax.swing.JButton btnInc46;
    private javax.swing.JButton btnInc47;
    private javax.swing.JButton btnInc48;
    private javax.swing.JButton btnInc49;
    private javax.swing.JButton btnInc5;
    private javax.swing.JButton btnInc50;
    private javax.swing.JButton btnInc51;
    private javax.swing.JButton btnInc52;
    private javax.swing.JButton btnInc53;
    private javax.swing.JButton btnInc6;
    private javax.swing.JButton btnInc7;
    private javax.swing.JButton btnInc8;
    private javax.swing.JButton btnInc9;
    private javax.swing.JButton btnMiniBurger;
    private javax.swing.JButton btnOrderList;
    private javax.swing.JButton btnOrderingViewExit;
    private javax.swing.JButton btnPayNow1;
    private javax.swing.JButton btnSides;
    private javax.swing.JButton btnSpBurger;
    private javax.swing.JTextField cb1;
    private javax.swing.JTextField cb2;
    private javax.swing.JTextField cb3;
    private javax.swing.JTextField cb4;
    private javax.swing.JTextField cb5;
    private javax.swing.JTextField d1;
    private javax.swing.JTextField d2;
    private javax.swing.JTextField d3;
    private javax.swing.JTextField d4;
    private javax.swing.JTextField d5;
    private javax.swing.JTextField d6;
    private javax.swing.JTextField dr1;
    private javax.swing.JTextField dr2;
    private javax.swing.JTextField dr3;
    private javax.swing.JTextField dr4;
    private javax.swing.JTextField dr5;
    private javax.swing.JTextField dr6;
    private javax.swing.JTextField g1;
    private javax.swing.JTextField g2;
    private javax.swing.JTextField g3;
    private javax.swing.JTextField g4;
    private javax.swing.JTextField g5;
    private javax.swing.JTextField g6;
    private javax.swing.JTextField h1;
    private javax.swing.JTextField h2;
    private javax.swing.JTextField h3;
    private javax.swing.JTextField h4;
    private javax.swing.JTextField h5;
    private javax.swing.JTextField h6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel100;
    private javax.swing.JPanel jPanel101;
    private javax.swing.JPanel jPanel102;
    private javax.swing.JPanel jPanel103;
    private javax.swing.JPanel jPanel104;
    private javax.swing.JPanel jPanel105;
    private javax.swing.JPanel jPanel106;
    private javax.swing.JPanel jPanel107;
    private javax.swing.JPanel jPanel108;
    private javax.swing.JPanel jPanel109;
    private javax.swing.JPanel jPanel110;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel112;
    private javax.swing.JPanel jPanel113;
    private javax.swing.JPanel jPanel114;
    private javax.swing.JPanel jPanel115;
    private javax.swing.JPanel jPanel116;
    private javax.swing.JPanel jPanel117;
    private javax.swing.JPanel jPanel118;
    private javax.swing.JPanel jPanel119;
    private javax.swing.JPanel jPanel120;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JPanel jPanel86;
    private javax.swing.JPanel jPanel87;
    private javax.swing.JPanel jPanel88;
    private javax.swing.JPanel jPanel89;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel90;
    private javax.swing.JPanel jPanel91;
    private javax.swing.JPanel jPanel92;
    private javax.swing.JPanel jPanel93;
    private javax.swing.JPanel jPanel94;
    private javax.swing.JPanel jPanel95;
    private javax.swing.JPanel jPanel96;
    private javax.swing.JPanel jPanel97;
    private javax.swing.JPanel jPanel98;
    private javax.swing.JPanel jPanel99;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableMyOrder;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel jlblSp1;
    private javax.swing.JLabel jlblSp2;
    private javax.swing.JLabel jlblSp3;
    private javax.swing.JLabel jlblSp4;
    private javax.swing.JLabel jlblSp5;
    private javax.swing.JLabel jlblSp6;
    private javax.swing.JLabel jlblSp7;
    private javax.swing.JLabel jlblSp8;
    private javax.swing.JLabel jlblSp9;
    private javax.swing.JTextField jtxtDiscount;
    private javax.swing.JTextField jtxtQty11;
    private javax.swing.JTextField jtxtQty12;
    private javax.swing.JTextField jtxtQty13;
    private javax.swing.JTextField jtxtQty14;
    private javax.swing.JTextField jtxtQty15;
    private javax.swing.JTextField jtxtQty16;
    private javax.swing.JTextField jtxtSubtotal;
    private javax.swing.JTextField jtxtTAX;
    private javax.swing.JTextField jtxtTotal;
    private javax.swing.JTextField mb1;
    private javax.swing.JTextField mb2;
    private javax.swing.JTextField mb3;
    private javax.swing.JTextField mb4;
    private javax.swing.JTextField mb5;
    private javax.swing.JTextField mb6;
    private javax.swing.JTextField s1;
    private javax.swing.JTextField s2;
    private javax.swing.JTextField s3;
    private javax.swing.JTextField s4;
    private javax.swing.JTextField s5;
    private javax.swing.JTextField s6;
    private javax.swing.JPanel sidebar;
    private javax.swing.JPanel sidebar3;
    // End of variables declaration//GEN-END:variables
}
