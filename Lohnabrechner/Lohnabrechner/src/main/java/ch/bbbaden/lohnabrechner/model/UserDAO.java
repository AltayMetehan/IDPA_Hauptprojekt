package ch.bbbaden.lohnabrechner.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


/**
 *
 * @author Metehan
 */
@ApplicationScoped
public class UserDAO {
    private final File USER_FILE;
    private final SAXBuilder builderUsers;

    public UserDAO() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        USER_FILE = new File(new File(path,"WEB-INF"),"user.xml");
        builderUsers = new SAXBuilder();
    }
    
    public User check(final String name, final String password){
        try {
            Document document = (Document) builderUsers.build(USER_FILE);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("user");
            for (int i = 0; i < list.size(); i++) {
                org.jdom2.Element node = (org.jdom2.Element) list.get(i);
                if (node.getChildText("name").equals(name) && node.getChildText("password").equals(password)) {
                    return new User(Integer.parseInt(node.getAttribute("id").getValue()), name, password, false);
                }
            }
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List getUsers(User user){
        List<User> ausgabe;
        ausgabe = new ArrayList();
        try {
            Document document = (Document) builderUsers.build(USER_FILE);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("user");
            for (int i = 0; i < list.size(); i++) {
                Element node = (Element) list.get(i);
                User u = new User(user.getId(), user.getName(), user.getPassword(), user.getIsAdminLogin());
                u.setId(Integer.parseInt(node.getAttribute("id").getValue()));
                u.setName(node.getChildText("name"));
                u.setPassword(node.getChildText("password"));
                ausgabe.add(u);
            }
        } catch (JDOMException | IOException  ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ausgabe;
    }
    
    public void create(String names, String passwords) throws JDOMException, IOException{
        Element name = new Element("name");
        Element password = new Element("password");
        Element user = new Element("user");
        
        user.addContent(name);
        user.addContent(password);
        
        Document document = (Document) builderUsers.build(this.USER_FILE);
        Element rootElement = document.getRootElement();
        rootElement.addContent(user);
        
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
    }
    
    public void delete(User user){
        List<User> userList;
        userList = new ArrayList<>();
        userList.remove(user);
    }
    
    public void setUser(User user) throws IOException {
        List<User> usersList;
        usersList = new ArrayList();
        User users = new User(user.getId(), user.getName(), user.getPassword(), user.getIsAdminLogin());
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");

        Element root = new Element("user");
        Element name = new Element("name");
        Element id = new Element("id");
        Element password = new Element("password");

        name.setText(users.getName());
        for (int i = 0; i < usersList.size(); i++) {
            id.setContent(users.getId()+ 1, id);
        }
        password.setText(users.getPassword());

        root.addContent(id);
        root.addContent(name);
        root.addContent(password);

        Document doc = new Document(root);

        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(doc, new FileWriter(path + "WEB-INF\\user.xml"));
    }
}
