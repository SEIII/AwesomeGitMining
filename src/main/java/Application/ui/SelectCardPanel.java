//package Application.ui;
//
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import javax.swing.JPanel;
//import javax.swing.JButton;
//
//import Application.ui.uiTools.RenderSetter;
//import Application.ui.uiTools.UIColor;
//
//public class SelectCardPanel extends JPanel{
//
//	/**
//	 *
//	 */
//	private static final long serialVersionUID = 1L;
//
//	int selectIndex = 0;
//	List<JButton> buttonList;
//	Font defaultFont = new Font("微软雅黑 light",Font.PLAIN,20);
//	Image border = new ImageIcon("pic/cards/border.png").getImage();
//
//	public SelectCardPanel(){
//
//		this.setLayout(null);
//		this.setBounds(0,0,880,40);
//		this.setBackground(UIColor.getGreen());
//		buttonList = new ArrayList<JButton>();
//
//	}
//
//	public void addCard(Icon img,MouseListener lis){
//
//		JButton button = new JButton(img);
//		button.setContentAreaFilled(false);
//		button.setBorderPainted(false);
//		button.addMouseListener(lis);
//		button.addMouseListener(new SelectCardListener());
//		buttonList.add(button);
//
//	}
//
//	public void addCard(Icon img,Icon rollOver,MouseListener lis){
//
//		JButton button = new JButton(img);
//		button.setRolloverIcon(rollOver);
//		button.setContentAreaFilled(false);
//		button.setBorderPainted(false);
//		button.addMouseListener(lis);
//		button.addMouseListener(new SelectCardListener());
//		buttonList.add(button);
//
//	}
//
//	public void addCard(String tag,MouseListener lis){
//
//		JButton button = new JButton(tag);
//		button.setFont(defaultFont);
//		button.setContentAreaFilled(false);
//		button.setBorderPainted(false);
//		button.addMouseListener(lis);
//		button.addMouseListener(new SelectCardListener());
//		buttonList.add(button);
//
//	}
//
//	public void addCard(String tag,Font font,MouseListener lis){
//
//		JButton button = new JButton(tag);
//		button.setFont(font);
//		button.setContentAreaFilled(false);
//		button.setBorderPainted(false);
//		button.addMouseListener(lis);
//		button.addMouseListener(new SelectCardListener());
//		buttonList.add(button);
//
//	}
//
//	public void init(){
//
//		for(int i=0;i<buttonList.size();i++){
//			JButton button = buttonList.get(i);
//			button.setBounds(100*i,0,100,40);
//			this.add(button);
//		}
//
//	}
//
//	protected void drawBorder(){
//
//		this.repaint();
//
//	}
//
//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(RenderSetter.setRender(g));
//		g.drawImage(border,selectIndex*100,0,100,40,this);
//	}
//
//	class SelectCardListener implements MouseListener{
//
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//			for(int i=0;i<buttonList.size();i++){
//				if(buttonList.get(i).equals(e.getComponent())){
//					selectIndex = i;
//					drawBorder();
//				}
//			}
//
//		}
//
//		@Override
//		public void mousePressed(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void mouseReleased(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void mouseExited(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}
//
//	}
//
//}
