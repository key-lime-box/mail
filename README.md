# Key Lime Mail
A library to simplify the use of Java Mail API. 

Currently, the library only contains a simplified API for reading a `javax.mail.Message`. Further work may add features to connect to mail stores or send messages. 

Pull requests are welcome!

# How to use
Very simple - wrap a `javax.mail.Message` instance by creating a new `org.keylimebox.mail.EmailMessage` 
and passing the message in the constructor.