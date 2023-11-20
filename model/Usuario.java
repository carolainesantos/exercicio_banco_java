package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import banco_dados.ConexaoBanco;

public class Usuario {
  private int id;
  private String nome;
  private String email;
  private String password;
  private static ArrayList<Usuario> usuarios = new ArrayList<>();
  
  // Construtor vazio para guardar novo usuario
  public Usuario() {
  }

   //Construtor
  public Usuario(String nome, String email, String password) {
    this.nome = nome;
    this.email = email;
    this.password = password;
    criarNoBanco(this);
  }

  public int criarNoBanco(Usuario usuario){
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("INSERT INTO usuario (nome, email, senha) values (?, ?, ?)");
      ps.setString(1, usuario.nome);
      ps.setString(2, usuario.email);
      ps.setString(3, usuario.password);
      int rs = ps.executeUpdate();
      conexao.close();
      return rs;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  // SET
  public void setUsuarios(Usuario usuario) {
    criarNoBanco(usuario);
  }
  public void setUsuarioById(int id) {
    this.id = id;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  
  // GET
  public static ArrayList<Usuario> getUsuarios() {
    return usuarios;
  }
  public int getUsuarioById() {
    return this.id;
  }
  public String getNome() {
    return this.nome; 
  }
  public String getEmail() {
    return this.email; 
  }
  public String getPassword() {
    return this.password; 
  }

  // Mostrar usuarios
  public static void mostrarUsuarios() {
     try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("SELECT * FROM usuario");
      ResultSet resultado = ps.executeQuery();
      while (resultado.next()) {
        System.out.println(resultado.getString("id") + " - " + resultado.getString("nome") + " - " + resultado.getString("email"));
      }
      conexao.close();
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  // Método Logar 
  public static void fazerLogin(String email, String senha) {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement query = conexao.prepareStatement("SELECT * FROM usuario WHERE email = ? AND senha = ?");
      query.setString(1, email);
      query.setString(2, senha);
      ResultSet resultado = query.executeQuery();
      while (resultado.next()) {
        System.out.println("Logado com sucesso");
        conexao.close();
        return;
      }
      conexao.close();
      System.out.println("Login ou senha invalidos");
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  // Excluir 
  public static int excluirUsuario(int id) {
     try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("DELETE FROM usuario WHERE id = ?");
      ps.setInt(1, id);

      int rs = ps.executeUpdate();
      conexao.close();
      return rs;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  // Alterar
  public static boolean alterarUsuario(int id, Usuario usuarioAlterar) {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement query = conexao.prepareStatement("UPDATE usuario SET nome = ?, email = ? WHERE id = ?");
      query.setString(1, usuarioAlterar.nome);
      query.setString(2, usuarioAlterar.email);
      query.setInt(3, id);

      int resultado = query.executeUpdate();
      conexao.close();
      // Se ele alterar um usuário vai retornar um então entra
      if(resultado > 0) {
        return true;
      }
      return false;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  // Método ToString
  public String toString() {
    return "Num Id: " + this.id + "\n" + "Nome: " + this.nome + "\n" + "Email: " + this.email + "\n";
  }
}

