package stuManager;

// import java.io.File;

import javax.swing.*;

import java.awt.*;

// ��¼ģ��

public class LoginClass {
	boolean packFrame = false;

	public LoginClass() {
		LoginFrame frame = new LoginFrame();
		if (packFrame) {
			frame.pack();
		} else {
			frame.validate();
		}
		// ���������С
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2); // ���ô���λ��
		frame.setVisible(true); // �Ƿ�ɼ�
	}

	// ������
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					// ������۷��
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				new LoginClass(); // ������¼����
			}
		});
	}
}
