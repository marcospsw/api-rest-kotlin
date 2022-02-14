# Estudos Kotlin

### Lições aprendidas sobre API REST

- **API REST**
    - A API REST vem para solucionar problemas de varios clientes poderem usar o mesmo back-end indepentente de como o
      cliente vai tratar e exibir essa informação
    - De acordo com as rotas trata o recurso recebido e o existente
    - Possui 4 métodos principais de chamada (GET - POST - PUT - DELETE)
    - Geralmente de acordo com os principios do Clean-Code e SOLID, organizada em camadas
- **Camada de Domínio (MODELS)**
    - É onde fica as estruturas a serem utilizadas pela regra de negócio e aplicação
    - Estruturas a serem utilizadas pelo negócio
    - Geralmente são ou estão ligadas a alguma entidade
- **Camada de Serviço (SERVICES)**
    - Camada onde é feito o processamento dos dados
    - @Service - Indica ao springboot que determinada classe é uma classe da camada de serviço permitindo que ela seja
      injetada em qualquer classe gerenciada pelo springboot
- **Camada de Controle (CONTROLLERS)**
    - Camada que gerencia as rotas da aplicação por meio de funções usando as notations dos métodos
    - @RestController - Indica ao springboot que determinada classe será um controller
    - @RequestMapping("/rota") - Mapeia qual será a rota que irá direcionar para esse controller
- **GET**
    - @GetMapping - @GetMapping("/complemento")Caso possua um complemento a rota
    - Para receber um param `@GetMapping("/{param}")` e `fun função(@PathVariable param: Tipo)`
    - Método responsavel por ler informações
- **POST**
    - @PostMapping - @PostMapping("/complemento")Caso possua um complemento a rota
    - Para receber o corpo da requisição `@PostMapping` e `fun função(@RequestBody body: Tipo)`
    - Método responsavel por criar informações
- **PUT**
    - @PutMapping - @PutMapping("/complemento")Caso possua um complemento a rota
    - Método responsavel por editar informações
- **DELETE**
    - @DeleteMapping - @DeleteMapping("/complemento")Caso possua um complemento a rota
    - Método responsavel por deletar informações
- **DTOs**
    - Servem para receber-mos e devolver-mos só o necessário tanto na entrada quanto na saída de dados(Para não
      trabalhar-mos em cima da entidade)
- **MAPPERs**
    - A classe mapper recebe a notação @Component
    - Podem ser usados para transformar o `DTO de entrada em Entidade` e `Entidade em DTO de Saída`
- **Enum**
    - Usado para padronizar atributos `enum class StatusTopico { NAO_RESPONDIDO, NAO_SOLUCIONADO }`
- **BeamValidation**
    - Validação de campos recebidos no DTO de entrada
    - No atributo desejado @field:validação("mensagem")
    - Também é necessario declarar @Valid no controller que recebe esse DTO
- **Tratando Exceções**
    - Podemos usar um DTO padrão de Erro
    - Podemos criar classes de exceções customizadas para que sejam chamadas no errorHander
    - `class ErroException(message: String): Exception(message)`
- **Handler de Exceções**
    - classe recebe a notação @RestControllerAdvice
    - São criadas funções para lançar o erro em caso de exceção
    - As funções recebem as notações @ExceptionHandler(ClasseDeExceção::class) @ResponseStatus(
      HttpStatus.StatusDesejado)
    - Também recebem como parâmetro a `exception: Exceção` e a `request: HttpServletRequest`
    - São executadas sempre que a classe de exceção é chamada e retornam o DTO de Erro