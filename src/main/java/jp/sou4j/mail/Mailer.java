package jp.sou4j.mail;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

public interface Mailer {
	/**
	 * <p>差出人メールアドレスを設定します。</p>
	 * @param from
	 */
	public void setFrom(InternetAddress from);

	/**
	 * <p>宛先となるメールアドレスを設定します。</p>
	 * @param addresses
	 */
	public void setTo(InternetAddress[] addresses);

	/**
	 * <p>宛先となるメールアドレスを設定します。</p>
	 * @param address
	 */
	public void setTo(InternetAddress address);

	/**
	 * <p>宛先となるメールアドレスを追加します。</p>
	 * @param address
	 */
	public void addTo(InternetAddress address);

	/**
	 * <p>CCとなるメールアドレスを設定します。</p>
	 * @param addresses
	 */
	public void setCc(InternetAddress[] addresses);

	/**
	 * <p>CCとなるメールアドレスを設定します。</p>
	 * @param address
	 */
	public void setCc(InternetAddress address);

	/**
	 * <p>CCとなるメールアドレスを追加します。</p>
	 * @param address
	 */
	public void addCc(InternetAddress address);

	/**
	 * <p>BCCとなるメールアドレスを設定します。</p>
	 * @param addresses
	 */
	public void setBcc(InternetAddress[] addresses);

	/**
	 * <p>BCCとなるメールアドレスを設定します。</p>
	 * @param address
	 */
	public void setBcc(InternetAddress address);

	/**
	 * <p>BCCとなるメールアドレスを追加します。</p>
	 * @param address
	 */
	public void addBcc(InternetAddress address);

	/**
	 * <p>メールの件名を設定します。</p>
	 * @param subject メールの件名
	 */
	public void setSubject(String subject);

	/**
	 * <p>メール本文を設定します。</p>
	 * @param body メール本文文字列
	 */
	public void setBody(String body);

	/**
	 * <p>メールを送信するために利用するSMTPサーバーのホスト名を設定します。</p>
	 * @param hostName SMTPサーバーホスト名
	 */
	public void setSmtpHost(String hostName);

	/**
	 * <p>添付ファイルを設定します。</p>
	 * @param file 添付ファイル
	 */
	public void setFile(DataSource file);

	/**
	 * <p>添付ファイルを設定します。</p>
	 * @param files 添付ファイル
	 */
	public void setFiles(DataSource[] files);

	/**
	 * <p>添付ファイルを追加します。</p>
	 * @param file 添付ファイル
	 */
	public void addFile(DataSource file);

	/**
	 * <p>メールを送信します。</p>
	 * @throws MessagingException
	 * @throws Exception
	 */
	public void send() throws MessagingException, Exception;
}
