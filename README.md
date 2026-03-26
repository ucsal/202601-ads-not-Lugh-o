# Sistema das Olimpíadas
Inicialmente o projeto estava sendo desenvolvido no seguinte repositório: https://github.com/Lugh-o/olimpiada

## Principais mudanças
A classe principal, App.java, foi quebrada em diversas classes, separando funcionalidades e responsabilidades em classes isoladas. 
- As entidades estão no pacote model, possuindo uma classe interna Builder, seguindo o Builder Pattern, com algumas validações de campos durante a instanciação do objeto; 
- A persistência de dados foi segregada em repositories;
- As opções do menu foram quebradas em commands, seguindo o Command Pattern;
- O input foi isolado por meio de uma interface e uma classe wrapper para lidar com a dependência;
- A instanciação do menu da aplicação está sendo feita por meio de uma Factory.
- Foi adicionado uma entrada de FEN inicial no cadastro de questão, devido a um erro quando a prova era aplicada sem esse campo em uma questão;

## SOLID
### Single Responsability Principle
Cada classe só deve ter um motivo para mudar: AplicarProvaService só aplica provas, ParticipanteRepository só gerencia o armazenamento de participantes, e assim por diante.

### Open/Closed Principle
É possível adicionar novas funcionalidades sem alterar código existente, por exemplo, para criar uma nova opção no menu, basta criar um Command que herda de MenuCommand e adicionar à lista de opções de menu na factory.

### Liskov Substitution Principle
Subclasses devem ser substituiveis por suas classes base, por exemplo, se eu possuir uma ProvaFinal que herda de Prova, qualquer lugar que usa prova vai funcionar com essa nova classe.

### Interface Segregation Principle
Cada interface expõe apenas os métodos necessários para sua utilização, não obrigando as classes que as usam a implementar métodos inúteis.

### Dependency Inversion Principle
Classes de alto nível não dependem de classes de baixo nível, elas utilizam interfaces abstratas, como IProvaRepository ou Input por exemplo.
