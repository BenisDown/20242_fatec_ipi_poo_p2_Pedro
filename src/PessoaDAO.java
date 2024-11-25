//data access object
//mapeamento objeto-relacional
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class PessoaDAO {
  public void cadastrar(Pessoa p) throws Exception {
    if (p == null) {
      throw new NullPointerException("Parametro p null");
    }
    var sql = "INSERT INTO tb_pessoa(nome, fone, email) VALUES (?, ?, ?)";

    try (var conexao = ConnectionFactory.conectar();
        var ps = conexao != null ? conexao.prepareStatement(sql) : null) {

      if (ps == null || conexao == null) {
        throw new SQLException("Erro ao preparar o comando ou conexão: " + sql);
      }

      ps.setString(1, p.getNome());
      ps.setString(2, p.getFone());
      ps.setString(3, p.getEmail());

      ps.execute();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new Exception("Erro ao cadastrar pessoa", e);
    }
  }

  public void atualizar(Pessoa p) throws Exception {
    if (p == null) {
      throw new NullPointerException("Parametro p null");
    }
    // 1. Especificar o comando SQL (UPDATE)
    var sql = "UPDATE tb_pessoa SET nome = ?, fone = ?, email = ? WHERE cod_pessoa = ?";

    try (var conexao = ConnectionFactory.conectar();
        var ps = conexao != null ? conexao.prepareStatement(sql) : null) {
      if (ps == null) {
        throw new SQLException("Erro ao preparar o comando: " + sql);
      }
      // 2. Substituir os eventuais placeholders
      ps.setString(1, p.getNome());
      ps.setString(2, p.getFone());
      ps.setString(3, p.getEmail());
      ps.setInt(4, p.getCodigo());
      // 3. Executar o comando
      ps.execute();
    }
  }
  public void apagar(Pessoa p) throws Exception {
    if (p == null) {
      throw new NullPointerException("Parametro p null");
    }
    
    var sql = "DELETE FROM tb_pessoa WHERE cod_pessoa = ?";
    
    try (var conexao = ConnectionFactory.conectar();
        var ps = conexao != null ? conexao.prepareStatement(sql) : null) {
      
      if (ps == null) {
        throw new SQLException("Erro ao preparar o comando: " + sql);
      }
      
      ps.setInt(1, p.getCodigo());
      ps.execute();
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }

  public List<Pessoa> listar(int c) throws Exception {
    List<Pessoa> pessoas = new ArrayList<>();
    String sql = "SELECT * FROM tb_pessoa";

    try (var conexao = ConnectionFactory.conectar();
        var ps = conexao != null ? conexao.prepareStatement(sql) : null;
        ResultSet rs = ps != null ? ps.executeQuery() : null) {

      if (rs != null) {
        while (rs.next()) {
          int codigo = rs.getInt("cod_pessoa");
          String nome = rs.getString("nome");
          String fone = rs.getString("fone");
          String email = rs.getString("email");
          Pessoa p = new Pessoa(codigo, nome, fone, email);
          pessoas.add(p);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return pessoas;
  }
}
