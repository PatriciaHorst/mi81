--API Gerenciador de Tarefas

Integrantes:
-RenanBDaboit
-PatriciaHorst

O projeto foi feito com o objetivo de concluir a atividade atribuída aos alunos feita pelo professor Carlos Fabio de Andrade sobre API Rest. 

A atividade pedia um sistema em que seja possível fazer um CRUD para um gerenciamento de tarefas que serâo armazenadas em uma lista no próprio Back-End do sistema.

Métodos(CRUD):
-listarTarefa (GET - "api/tarefas") <br>
	Retorna a lista de tarefas cadastradas no sistema com status 200 (OK)

-buscarTarefa (GET - "api/tarefas/{id}") <br>
	Parâmetros <br>
		@PathVariable Integer id - ID da tarefa para busca
	
	Retorna a tarefa do ID indicado na URI com status 200 (OK)

-cadastrar (POST - "api/tarefas") <br>
	Parâmetros <br>
		@RequestBody TarefaDto tarefaDto - Dados da tarefa para cadastrar
	
	Retorna status 200 (OK) com o cadastro da tarefa

-atualizar (PUT - "api/tarefas/{id}") <br>
	Parâmetros <br>
		@PathVariable Integer id - ID da tarefa para a atualização <br>
		@RequestBody TarefaDto tarefaDto - Dados da tarefa atualizado
		
	Retorna status 200 (OK) caso atualização ocorreu corretamente ou então 404 (Not Found) caso não tenha sido encontrado uma tarefa com aquele ID	

-concluir (PATCH - "api/tarefas/{id}") <br>
	Parâmetros <br>
		@PathVariable Integer id - ID da tarefa para a conclusão
	
	Retorna status 200 (OK) caso conclusão ocorreu corretamente ou então 404 (Not Found) caso não tenha sido encontrado uma tarefa com aquele ID	

-excluir (DELETE - "api/tarefas/{id}") <br>
	Parâmetros <br>
		@PathVariable Integer id - ID da tarefa para a exclusão

	Retorna status 204 (No Content) caso conclusão ocorreu corretamente ou então 404 (Not Found) caso não tenha sido encontrado uma tarefa com aquele ID	
