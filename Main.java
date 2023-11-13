/*  Instruções: Criar uma entidade simples de usuário: id, name, email, password.
Criar um menu para as operações de Criar, Alterar, Excluir, Visualizar e Logar.
Todas as operações devem fazer conexões com o banco de dados.*/ 

import java.util.Scanner;
import model.Usuario;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner (System.in);

    int opcao = 0;
    boolean temErro = false;

    do {
      System.out.println("\n");
      System.out.println("+-------------------------------------+");
      System.out.println("|  Sistema de controle de Usuários    |"); 
      System.out.println("|     [0] - Sair do Programa          |");
      System.out.println("|     [1] - Criar usuario             |");
      System.out.println("|     [2] - Alterar usuario           |");
      System.out.println("|     [3] - Excluir usuario           |");
      System.out.println("|     [4] - Visualizar usuario        |");
      System.out.println("|     [5] - Logar                     |");
      System.out.println("+-------------------------------------+");
      System.out.println("Escolha uma opção: ");

      try {
        opcao = sc.nextInt();
      } catch (Exception e) {
        opcao = 6;
      }

      switch (opcao) {
        case 0: {
          System.out.println("Encerrando Programa.");
          break;
        }
        case 1: {
          do {
            System.out.println("\n\tCadastrando Usuario");
            
            System.out.println("\nDigite seu nome: ");
            sc.nextLine();
            String nome = sc.nextLine();
            
            System.out.println("\nDigite seu email : ");
            String email = sc.nextLine();

            System.out.println("\nDigite uma senha: ");
            String password = sc.nextLine();
           
            try {
              if(nome.length() <= 2 || email.length() <5 || password.length() <5) {
                throw new Exception("Tamanho inválido");
              }
              temErro = false;
              Usuario usuario = new Usuario(nome, email, password);
              System.out.println("\n"+ usuario);
              System.out.println("\nUsuario cadastrado! \n");
              System.out.println("Nome do usuario: " + nome);
              System.out.println("Email: " + email);
              System.out.println("Identificador (ID): " + usuario.getUsuarioById());

            } catch (Exception e) {
              System.out.println("Erro: " + e.getMessage());
              temErro = true;
            }
          } while(temErro);
          break;
        }
        case 2: {
          do {
            System.out.println("\n\t Alterando dados do Usuario\n");
            Usuario usuarioAlterar = new Usuario();

            System.out.println("Digite o número do Id: ");
            int altId = sc.nextInt();
            
            System.out.println("Informe o novo nome:");
            sc.nextLine();
            usuarioAlterar.setNome(sc.nextLine());

            System.out.println("Informe o novo email:");
            usuarioAlterar.setEmail(sc.nextLine());

            boolean resultado = Usuario.alterarUsuario(altId, usuarioAlterar);

            if(resultado) {
              System.out.println("Sucesso");
            }
          } while (temErro);
          break;
        }
        case 3: {
          do {
            System.out.println("\n\t Excluindo Usuario\n");
            System.out.println("Digite o número do Id: ");
            int idDel = sc.nextInt();

            int usuarioExcluido = Usuario.excluirUsuario(idDel);
            if(usuarioExcluido == 1){
              System.out.println("\nUsuario deletado.");
            }
          } while(temErro);
          break;
        }
        case 4: {
          do {
            System.out.println("\n\t Visualizar Usuario(s)\n");

           Usuario.mostrarUsuarios();

          } while(temErro);
          break;
        }
        case 5: {
           do {
            System.out.println("\n\t Logar \n");
            System.out.println("Digite seu email: ");
            String email = sc.next(); 

            System.out.println("Digite a senha cadastrada: ");
            String senha = sc.next();

            Usuario.fazerLogin(email, senha);
           } while(temErro);
          break;
        }       
      } // Fecha switch
    } while (opcao !=0);
    sc.close(); 
  }
}
