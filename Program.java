package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entities.Aluno;
import jdbc.AlunoJDBC;


public class Program {

	public static void main(String[] args) {

		try {

			int opcao = 0;
			Scanner console = new Scanner(System.in);
			AlunoJDBC acao = new AlunoJDBC();

			do {
				System.out.println("####### Menu #######"
						+ "\n1 - Cadastrar"
						+ "\n2 - Listar"
						+ "\n3 - Alterar"
						+ "\n4 - Excluir"
						+ "\n5 - Sair");
				System.out.println("\n\tOpção:");
				opcao = Integer.parseInt(console.nextLine());

				if (opcao == 1) {
					Aluno a = new Aluno();
					System.out.println("\n ### Cadastrar Aluno ### \n\r");
					System.out.print("Nome: ");
					a.setNome(console.nextLine());
					System.out.print("Sexo: ");
					a.setSexo(console.nextLine());
					System.out.print("Data de Nascimento (dd/mm/aaaa): ");
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					a.setDt_nasc(LocalDate.parse(console.nextLine(), formato));
					acao.salvar(a);
					console.nextLine();
					System.out.println("\n\n\n\n");
				} else if (opcao == 2) {
					List<Aluno> alunos = acao.listar();
					System.out.println("\n ### Listar Alunos ### \n\r");
					for (Aluno aluno : alunos) {
						System.out.println("ID: " + aluno.getId());
						System.out.println("Nome: " + aluno.getNome());
						System.out.println("Sexo: " + aluno.getSexo());
						System.out.println("Data de Nascimento: " + aluno.getDt_nasc());
						System.out.println("---------------------");
					}
					console.nextLine();
					System.out.println("\n\n\n\n");
				} else if (opcao == 3) {
					System.out.println("\n ### Alterar Aluno ### \n\r");
					System.out.print("Digite o ID do aluno a ser alterado: ");
					int id = Integer.parseInt(console.nextLine());
					Aluno aluno = new Aluno();
					aluno.setId(id);
					System.out.print("Nome: ");
					aluno.setNome(console.nextLine());
					System.out.print("Sexo: ");
					aluno.setSexo(console.nextLine());
					System.out.print("Data de Nascimento (dd/mm/aaaa): ");
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					aluno.setDt_nasc(LocalDate.parse(console.nextLine(), formato));
					acao.alterar(aluno);
					console.nextLine();
					System.out.println("\n\n\n\n");
				} else if (opcao == 4) {
					System.out.println("\n ### Excluir Aluno ### \n\r");
					System.out.print("Digite o ID do aluno a ser excluído: ");
					int id = Integer.parseInt(console.nextLine());
					acao.apagar(id);
					console.nextLine();
					System.out.println("\n\n\n\n");
				}

			} while (opcao != 5);

		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
	}
}