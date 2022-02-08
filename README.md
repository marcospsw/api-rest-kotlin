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
    - 