import javax.swing.JOptionPane;
public class App {
    public static void main(String[] args) throws Exception {
        var menu = "1-Cadastrar\n2-Listar\n3-Atualizar\n4-Apagar\n5-Buscar\n0-Sair\n";
        int op = 0;
        do{
          op = Integer.parseInt(
            JOptionPane.showInputDialog(menu)
          );
          switch(op){
            case 1:{
              var nome = JOptionPane.showInputDialog("Nome?");
              if(nome == null) break;
              var fone = JOptionPane.showInputDialog("Fone?");
              if(fone == null) break;
              var email = JOptionPane.showInputDialog("E-mail?");
              if(email == null) break;
              var p = new Pessoa(0, nome, fone, email);
              var dao = new PessoaDAO();
              try {
                dao.cadastrar(p);
              } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
              }
              break;
            }
            case 2:{
              var dao = new PessoaDAO();
              try {
                var pessoas = dao.listar(0);
                JOptionPane.showMessageDialog(null, pessoas);
              } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
              }
              break;
            }
            case 3:{
              //capturar codigo, nome fone e e-mail
              var capturar = JOptionPane.showInputDialog(null,"Código?");
              if(capturar == null) break;
              try {
                var codigo = Integer.parseInt(capturar);
                //atualizar nome, fone e e-mail pessoa cujo codigo foi especificado
                var novoNome = JOptionPane.showInputDialog("Novo nome?");
                if(novoNome == null) break;
                var novoFone = JOptionPane.showInputDialog("Novo fone?");
                if(novoFone == null) break;
                var novoEmail = JOptionPane.showInputDialog("Novo e-mail?");
                if(novoEmail == null) break;
                var pessoa = new Pessoa(codigo, novoNome, novoFone, novoEmail);
                var dao = new PessoaDAO();
                try {
                  dao.atualizar(pessoa);
                } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, e.getMessage());
                }
              } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Código inválido!");
              }
              break;
            }
            case 4: {
              var codigo = JOptionPane.showInputDialog("Código?");
              if(codigo == null) break;
              try {
                var p = new Pessoa(Integer.parseInt(codigo));
                var dao = new PessoaDAO();
                try {
                  dao.apagar(p);
                } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, e.getMessage());
                }
              } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Código inválido!");
              }
              break;
            }
            case 5:{
              //capturar uma letra
              //listar todas as pessoas cujo nome começa com essa letra
              var letra = JOptionPane.showInputDialog("Letra?");
              if(letra == null) break;
              try {
                var dao = new PessoaDAO();
                var pessoas = dao.listar(letra.charAt(0));
                JOptionPane.showMessageDialog(null, pessoas);
              } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
              }
              break;
            }
            case 0:{
              JOptionPane.showMessageDialog(null, "Até!");
            }            
          }
        }while(op != 0);
      }
}
