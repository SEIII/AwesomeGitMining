//package Application.ui;
//
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import Application.AppContextSupport;
//import Application.business_logic.bl.ReposInfo;
//import Application.common.SearchPage;
//import Application.ui.InfoLabel.BaseItem;
//import Application.ui.InfoLabel.ReposItem;
//
//public class ReposPagePanel extends PagePanel<ReposInfo>{
//
//	/**
//	 *
//	 */
//	private static final long serialVersionUID = 1L;
//
//	//@Autowired
//	MainController c;
//
//	SelectCardPanel cardPanel;
//
//    Icon general;
//    Icon rolloverGeneral;
//    Icon star;
//    Icon rolloverStar;
//    Icon fork;
//    Icon rolloverFork;
//    Icon contributor;
//    Icon rolloverContributor;
//
//	public ReposPagePanel(SearchPage<ReposInfo> page) {
//		super(page);
//		// TODO Auto-generated constructor stub
//		c = AppContextSupport.getApplicationContext().getBean(MainController.class);
//
//		general = new ImageIcon("pic/cards/general.png");
//		rolloverGeneral = new ImageIcon("pic/cards/general2.png");
//		star = new ImageIcon("pic/cards/star.png");
//		rolloverStar = new ImageIcon("pic/cards/star2.png");
//		fork = new ImageIcon("pic/cards/fork.png");
//		rolloverFork = new ImageIcon("pic/cards/fork2.png");
//		contributor = new ImageIcon("pic/cards/contributor.png");
//		rolloverContributor = new ImageIcon("pic/cards/contributor2.png");
//
//		cardPanel = new SelectCardPanel();
//		cardPanel.addCard(general, rolloverGeneral,new CardClickListener(0));
//		cardPanel.addCard(star, rolloverStar,new CardClickListener(1));
//		cardPanel.addCard(fork, rolloverFork,new CardClickListener(2));
//		cardPanel.addCard(contributor, rolloverContributor,new CardClickListener(3));
//		cardPanel.init();
//		cardPanel.setBounds(0,0,880,40);
//		this.add(cardPanel);
//		this.validate();
//		this.repaint();
//
//	}
//
//	@Override
//	protected BaseItem getItem(ReposInfo info) {
//		// TODO Auto-generated method stub
//		ReposItem item = new ReposItem(info);
//		return item;
//	}
//
//	class CardClickListener implements MouseListener{
//
//		private int index;
//
//		public CardClickListener(int index){
//			this.index = index;
//		}
//
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			// TODO Auto-generated method stub
//			switch(index){
//			case 0: page = c.getSortByGeneral(); break;
//			case 1: page = c.getSortByStar(); break;
//			case 2: page = c.getSortByFork(); break;
//			case 3: page = c.getSortByContributor(); break;
//			}
//
//			pageNum.setText("1/"+page.totalPageNum());
//			initList(page.getCurrentContent());
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
