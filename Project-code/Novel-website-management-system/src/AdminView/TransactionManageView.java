package AdminView;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Adminmodel.Book;
import Adminmodel.Comment;
import Adminmodel.Transaction;
import Dao.BookDao;
import Dao.TransactionDao;
import util.DLUtil;
import util.StringUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TransactionManageView extends JFrame {

	private JPanel contentPane;
	private JTable transactiontable;
	private JTextField idtextField;
    private DLUtil dlutil=new DLUtil();
    private BookDao bookdao=new BookDao();
    private JComboBox transactioncomboBox;
    private TransactionDao transactiondao=new TransactionDao();
    private JLabel lblNewLabel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransactionManageView frame = new TransactionManageView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TransactionManageView() {
		setTitle("\u4EA4\u6613\u4FE1\u606F\u7BA1\u7406");
		setBounds(100, 100, 937, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ets) {
				TransactionSelect(ets);
			}
		});
		btnNewButton.setIcon(new ImageIcon(TransactionManageView.class.getResource("/images/\u67E5\u8BE2(3).png")));
		btnNewButton.setFont(new Font("????????", Font.PLAIN, 23));
		btnNewButton.setBounds(662, 56, 131, 43);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 140, 855, 295);
		contentPane.add(scrollPane);
		
		transactiontable = new JTable();
		transactiontable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TableClick(e);
			}
		});
		transactiontable.setFont(new Font("????????", Font.PLAIN, 16));
		transactiontable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7528\u6237ID", "\u5C0F\u8BF4ID", "\u5C0F\u8BF4\u540D\u79F0", "\u4EA4\u6613id", "\u4EA4\u6613\u91D1\u989D"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		transactiontable.getColumnModel().getColumn(2).setPreferredWidth(139);
		scrollPane.setViewportView(transactiontable);
		
		JLabel lblNewLabel = new JLabel("\u5C0F\u8BF4\u540D\u79F0\uFF1A");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(TransactionManageView.class.getResource("/images/\u56FE\u4E66(1).png")));
		lblNewLabel.setFont(new Font("????????", Font.BOLD, 25));
		lblNewLabel.setBounds(164, 59, 186, 36);
		contentPane.add(lblNewLabel);
		
	    transactioncomboBox = new JComboBox();
	    transactioncomboBox.setFont(new Font("????????", Font.PLAIN, 15));
		transactioncomboBox.setBounds(360, 56, 240, 43);
		contentPane.add(transactioncomboBox);
		
		idtextField = new JTextField();
		idtextField.setFont(new Font("????????", Font.PLAIN, 18));
		idtextField.setEditable(false);
		idtextField.setBounds(154, 490, 131, 43);
		contentPane.add(idtextField);
		idtextField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("\u5220\u9664");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent etd) {
				TransactionDelete(etd);
			}
		});
		btnNewButton_1.setFont(new Font("????????", Font.PLAIN, 22));
		btnNewButton_1.setIcon(new ImageIcon(TransactionManageView.class.getResource("/images/\u5220\u9664(1).png")));
		btnNewButton_1.setBounds(390, 488, 139, 43);
		contentPane.add(btnNewButton_1);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TransactionManageView.class.getResource("/png/16.jpg")));
		lblNewLabel_1.setBounds(0, 0, 933, 577);
		contentPane.add(lblNewLabel_1);
		
		
		//??????????????
				this.fillnovelType("search");
				this.fillnovelType("modify");
				
		//????????????
				this.filltable(new Transaction());
	}
	
	
	//????????????
	private void TransactionDelete(ActionEvent etd) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String id=this.idtextField.getText();
				if(StringUtil.isEmpty(id)) {
					JOptionPane.showMessageDialog(null, "??????????????????");
					return;	
				}
				int n=JOptionPane.showConfirmDialog(null,"????????????????????");
				if(n==0) {
					Connection conn=null;
					try {
						conn=dlutil.getCon();
						int deleteNum=transactiondao.delete(conn, id);
						if(deleteNum==1) {
							JOptionPane.showMessageDialog(null, "????????");	
							this.idtextField.setText("");
							this.filltable(new Transaction());//????????????
						}else {
							JOptionPane.showMessageDialog(null, "????????");		
						}
					}catch(Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "????????");	
					}finally {
						try {
							dlutil.CloseCon(conn);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
	}

//????????????????
private void TransactionSelect(ActionEvent ets) {
		// TODO Auto-generated method stub
	//????????????????????
			Book book=(Book) this.transactioncomboBox.getSelectedItem();
			int novelid=book.getNovelid();
			Transaction transaction=new Transaction(novelid);//????????
			this.filltable(transaction);
			this.transactioncomboBox.setSelectedIndex(0);
			
	}

	//????????????
	protected void TableClick(MouseEvent e) {
		// TODO Auto-generated method stub
	int row=this.transactiontable.getSelectedRow();//????????
	//????????????
	this.idtextField.setText((String) transactiontable.getValueAt(row, 3));
	}

		//????????
		private void filltable(Transaction transaction) {
			DefaultTableModel dtm=(DefaultTableModel)transactiontable.getModel();//????model????????????
			dtm.setRowCount(0);//??????0????????????
			Connection conn=null;
			try {
				conn=dlutil.getCon();//??????????????
				ResultSet rs=transactiondao.list(conn, transaction);
				
				//??????????????
				while(rs.next()) {
					Vector v=new Vector();
						v.add(rs.getString("userid"));
					v.add(rs.getString("novelid"));
					v.add(rs.getString("novelname"));
				v.add(rs.getString("transactionid"));
					v.add(rs.getString("novelmoney"));
					dtm.addRow(v);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					dlutil.CloseCon(conn);//??????????????
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	
	
	//????????????????????
	//??????????
	private void fillnovelType(String type) {
		Connection conn=null;
		Book book=null;
		try {
			conn=dlutil.getCon();
			ResultSet rs=bookdao.list(conn,new Book());
			if("search".equals(type)) {
				book=new Book();
				book.setNovelname("??????.....");
				book.setNovelid(-1);
				this.transactioncomboBox.addItem(book);
			}
			//??????????????????????????
			while(rs.next()) {
				book=new Book();
				book.setNovelname(rs.getString("novelname"));
				book.setNovelid(rs.getInt("novelid"));
				if("search".equals(type)) {
					this.transactioncomboBox.addItem(book);
				}else if("modify".equals(type)){
//					this.commentcomboBox.addItem(book);//????????????????????????????????????????
				}
			}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			dlutil.CloseCon(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
}
