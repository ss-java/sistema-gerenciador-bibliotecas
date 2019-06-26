package view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class BorderFacade {
    
    private JPanel formPanel;
    
    public BorderFacade(){
        // Cria o painel com as bordas para separar 
    	// o conteudo visualmente
        this.formPanel = new JPanel();
        this.formPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.formPanel.setBounds(12, 12, 370, 494);
        this.formPanel.setLayout(null);
    }
    
    public void add(Component target){
        formPanel.add(target);
    }
    
    public JPanel getPanel(){
        return this.formPanel;
    }
}
