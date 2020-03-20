package jp.sou4j.mail;

import java.util.Arrays;
import java.util.List;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import jp.sou4j.util.StringUtils;

public abstract class AbstractTextMailer implements Mailer {

	private String host = "localhost";
	private InternetAddress from;
	private InternetAddress[] toAddresses;
	private InternetAddress[] ccAddresses;
	private InternetAddress[] bccAddresses;
	private InternetAddress errorsTo;
	private InternetAddress returnPath;
	private String subject;
	private String body;
	private DataSource[] files;

	/**
	 * <p>メールを送信するために利用するSMTPサーバーのホスト名を設定します。</p>
	 * @param hostName SMTPサーバーホスト名
	 */
	@Override
	public void setSmtpHost(final String hostName) {
		if( StringUtils.isNullOrEmpty(hostName) ) throw new IllegalArgumentException("Method argument 'hostName' is null or empty.") ;
		this.host = hostName;
	}

	/**
	 * <p>SMTPサーバのホスト名を取得します。</p>
	 * @return SMTPサーバのホスト名
	 */
	protected String getSmtpHost() {
		return this.host;
	}

	/**
	 * <p>送信元メールアドレスを設定します。</p>
	 * @param from
	 */
	@Override
	public void setFrom(InternetAddress address) {
		if( address == null ) throw new IllegalArgumentException("Method argument 'address' is null.") ;
		this.from = address;
	}

	/**
	 * <p>送信元メールアドレスを取得します。</p>
	 * @return 送信元メールアドレス
	 */
	protected InternetAddress getFrom() {
		return this.from;
	}

	/**
	 * <p>宛先となるメールアドレスを設定します。</p>
	 * @param addresses
	 */
	@Override
	public void setTo(InternetAddress[] addresses) {
		if( addresses == null ) throw new IllegalArgumentException("Method argument 'addresses' is null.") ;
		this.toAddresses = addresses;
	}

	/**
	 * <p>宛先となるメールアドレスを設定します。</p>
	 * @param address
	 */
	@Override
	public void setTo(InternetAddress address) {
		if( address == null ) throw new IllegalArgumentException("Method argument 'address' is null.") ;
		this.toAddresses = this.singleToArray(address);
	}

	/**
	 * <p>宛先となるメールアドレスを追加します。</p>
	 * @param address
	 */
	@Override
	public void addTo(InternetAddress address) {
		if( address == null ) throw new IllegalArgumentException("Method argument 'address' is null.") ;
		this.toAddresses = this.addAddress(this.toAddresses, address);
	}

	/**
	 * <p>宛先となるメールアドレスを取得します。</p>
	 * @return 宛先となるメールアドレス
	 */
	protected InternetAddress[] getTo() {
		return this.toAddresses;
	}

	/**
	 * <p>CCとなるメールアドレスを設定します。</p>
	 * @param addresses
	 */
	@Override
	public void setCc(InternetAddress[] addresses) {
		if( addresses == null ) throw new IllegalArgumentException("Method argument 'addresses' is null.") ;
		this.ccAddresses = addresses;
	}

	/**
	 * <p>CCとなるメールアドレスを設定します。</p>
	 * @param address
	 */
	@Override
	public void setCc(InternetAddress address) {
		if( address == null ) throw new IllegalArgumentException("Method argument 'address' is null.") ;
		this.ccAddresses = this.singleToArray(address);
	}

	/**
	 * <p>CCとなるメールアドレスを追加します。</p>
	 * @param address
	 */
	@Override
	public void addCc(InternetAddress address) {
		if( address == null ) throw new IllegalArgumentException("Method argument 'address' is null.") ;
		this.ccAddresses = this.addAddress(this.ccAddresses, address);
	}

	/**
	 * <p>CCとなるメールアドレスを取得します。</p>
	 * @return CCとなるメールアドレス
	 */
	protected InternetAddress[] getCc() {
		return this.ccAddresses;
	}

	/**
	 * <p>BCCとなるメールアドレスを設定します。</p>
	 * @param addresses
	 */
	@Override
	public void setBcc(InternetAddress[] addresses) {
		if( addresses == null ) throw new IllegalArgumentException("Method argument 'addresses' is null.") ;
		this.bccAddresses = addresses;
	}

	/**
	 * <p>BCCとなるメールアドレスを設定します。</p>
	 * @param address
	 */
	@Override
	public void setBcc(InternetAddress address) {
		if( address == null ) throw new IllegalArgumentException("Method argument 'address' is null.") ;
		this.bccAddresses = this.singleToArray(address);
	}

	/**
	 * <p>BCCとなるメールアドレスを追加します。</p>
	 * @param address
	 */
	@Override
	public void addBcc(InternetAddress address) {
		if( address == null ) throw new IllegalArgumentException("Method argument 'address' is null.") ;
		this.bccAddresses = this.addAddress(this.bccAddresses, address);
	}

	/**
	 * <p>BCCとなるメールアドレスを取得します。</p>
	 * @return BCCとなるメールアドレス
	 */
	protected InternetAddress[] getBcc() {
		return this.bccAddresses;
	}

	/**
	 * <p>Errors-To となるメールアドレスを追加します。</p>
	 */
	public void setErrorsTo(InternetAddress address) {
		if( address == null ) throw new IllegalArgumentException("Method argument 'address' is null.");
		this.errorsTo = address;
	}

	/**
	 * <p>Errors-To となるメールアドレスを返却します。</p>
	 * @return Errors-To となるメールアドレス
	 */
	protected InternetAddress getErrorsTo() {
		return this.errorsTo;
	}

	/**
	 * <p>Return-Path となるメールアドレスを追加します。</p>
	 */
	public void setReturnPath(InternetAddress address) {
		if( address == null ) throw new IllegalArgumentException("Method argument 'address' is null.");
		this.returnPath = address;
	}

	/**
	 * <p>Return-Path となるメールアドレスを返却します。</p>
	 * @return Return-Path となるメールアドレス
	 */
	protected InternetAddress getReturnPath() {
		return this.returnPath;
	}

	/**
	 * <p>メールの件名を設定します。</p>
	 * @param subject メールの件名
	 */
	@Override
	public void setSubject(String subject) {
		if( subject == null ) throw new IllegalArgumentException("Method argument 'subject' is null.") ;
		this.subject = subject;
	}

	/**
	 * <p>メールの件名を取得します。</p>
	 * @return メールの件名
	 */
	protected String getSubject() {
		return this.subject;
	}

	/**
	 * <p>メール本文を設定します。</p>
	 * @param body メール本文文字列
	 */
	@Override
	public void setBody(String body) {
		if( body == null ) throw new IllegalArgumentException("Method argument 'body' is null.") ;
		this.body = body;
	}

	/**
	 * <p>メール本文を取得します。</p>
	 * @return メール本文文字列
	 */
	protected String getBody() {
		return this.body;
	}

	/**
	 * <p>添付ファイルを取得します。</p>
	 * @return 添付ファイル
	 */
	public DataSource[] getFiles() {
		return this.files;
	}

	/**
	 * <p>添付ファイルを設定します。</p>
	 * @param file 添付ファイル
	 */
	@Override
	public void setFile(DataSource file) {
		if( file == null ) throw new IllegalArgumentException("Method argument 'file' is null.") ;
		this.files = new DataSource[] { file };
	}

	/**
	 * <p>添付ファイルを設定します。</p>
	 * @param files 添付ファイル
	 */
	@Override
	public void setFiles(DataSource[] files) {
		if( files == null ) throw new IllegalArgumentException("Method argument 'files' is null.") ;
		this.files = files;
	}

	/**
	 * <p>添付ファイルを追加します。</p>
	 * @param file 添付ファイル
	 */
	@Override
	public void addFile(DataSource file) {
		if( files == null ) throw new IllegalArgumentException("Method argument 'files' is null.") ;

		List<DataSource> fileList = Arrays.asList(this.files);
		fileList.add(file);
		this.files = fileList.toArray(new DataSource[0]);
	}

	/**
	 * <p>メールを送信します。</p>
	 * @throws MessagingException メール送信にてエラーが発生した場合
	 */
	@Override
	public abstract void send() throws MessagingException;

	/**
	 * InternetAddressオブジェクトを、要素数が1のInternetAddressクラスの配列にして返却します。
	 * @param address
	 * @return
	 */
	private InternetAddress[] singleToArray(final InternetAddress address) {
		if( address == null ) throw new IllegalArgumentException("Method argument 'address' is null.") ;
		InternetAddress[] addresses = new InternetAddress[1];
		addresses[0] = address;
		return addresses;
	}

	/**
	 * 引数で指定した配列 addresses の要素に address を追加した配列を返却します。
	 * @param addresses
	 * @param address
	 * @return
	 */
	private InternetAddress[] addAddress(InternetAddress[] addresses, InternetAddress address) {
		if( addresses == null ) throw new IllegalArgumentException("Method argument 'addresses' is null.") ;
		if( address   == null ) throw new IllegalArgumentException("Method argument 'address' is null.") ;

		InternetAddress[] newAddresses = new InternetAddress[addresses.length + 1];
		for (int i = 0; i < addresses.length; i++) {
			newAddresses[i] = addresses[i];
		}
		newAddresses[addresses.length] = address;

		return newAddresses;
	}
}
