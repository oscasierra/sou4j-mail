package jp.sou4j.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * <p>UTF-8のテキストメールを送信するための簡易メールクラスです。</p>
 */
public class UTF8TextMailer extends AbstractTextMailer {

	/**
	 * <p>メールを送信します。</p>
	 * @throws MessagingException
	 * @throws Exception
	 */
	@Override
	public void send() throws MessagingException {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", super.getSmtpHost());
		Session session = Session.getInstance(properties, null);

		try {
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setHeader("Content-Type", "text/plain; charset=UTF-8");
			mimeMessage.setHeader("Content-Transfer-Encoding", "8bit");
			mimeMessage.setFrom(super.getFrom());
			mimeMessage.setSentDate(new Date());
			mimeMessage.setRecipients(Message.RecipientType.TO, super.getTo());
			mimeMessage.setRecipients(Message.RecipientType.CC, super.getCc());
			mimeMessage.setRecipients(Message.RecipientType.BCC, super.getBcc());
			mimeMessage.setSubject(super.getSubject(), "UTF-8");
			mimeMessage.setText(super.getBody() + "\n", "UTF-8");
			// 添付ファイルあり
			if (super.getFiles() != null) {
				// MultiPart
				MimeMultipart multiPart = new MimeMultipart();
				// TextMail
				MimeBodyPart textPart = new MimeBodyPart();
				textPart.setText(super.getBody() + "\n", "UTF-8");
				multiPart.addBodyPart(textPart);

				// Attached File
				MimeBodyPart filePart;
				for (DataSource file : super.getFiles()) {
					filePart = new MimeBodyPart();
					filePart.setDataHandler(new DataHandler(file));
					filePart.setFileName(MimeUtility.encodeText(file.getName(), "UTF-8", "B"));
					multiPart.addBodyPart(filePart);
				}
				// メールにMultiPartを設定
				mimeMessage.setContent(multiPart);
			}

			Transport.send(mimeMessage);
		}
		catch (UnsupportedEncodingException exception) {
			throw new MessagingException(exception.getMessage(), exception);
		}
	}
}
