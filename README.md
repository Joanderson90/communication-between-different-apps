## MI Concorrência e Conectividade - Problema 1 Internet das Coisas.
Este relatório tem o objetivo de descrever os detalhes do desenvolvimento de um sistema baseado na Arquitetura em Rede, utilizando a linguagem de programação Java em conjunto com o framework Angular.

## Introdução
A comunicação é essencial em um sistema distribuído. Entretanto, nem sempre esses sistemas possuem a mesma arquitetura, isto é, falam a mesma “língua”. Desta forma, de modo geral, se faz necessário um outro sistema que possa viabilizar a troca de mensagens, consequentemente, atuando como um intermediador da comunicação.


Nesse sentido, foi solicitado aos alunos do curso de Engenharia de Computação da Universidade Estadual de Feira de Santana(UEFS), que desenvolvessem um sistema para permitir a comunicação e a troca de dados entre aplicações com infraestrutura diferentes.

Este sistema foi desenvolvido utilizando sockets para permitir a comunicação cliente-servidor entre um broker(cliente) e um dispositivo virtual(servidor e sensor). Além disso, foi utilizada uma API REST para permitir a comunicação entre o dispositivo virtual e uma aplicação simples (interface), tendo como intermediador da comunicação o broker citado anteriormente. Consequentemente, o broker foi utilizado para permitir a comunicação entre o dispositivo virtual e a interface.  Já que a o dispositivo virtual não possuía capacidade para rodar servidores HTTP.

Após o desenvolvimento do sistema em questão, foi utilizado containers docker para virtualização da aplicação e execução dos testes.

## Fundamentação Teórica
- Um servidor geralmente é descrito como um sistema computacional que provê serviços para serem consumidos por usuários ou até mesmo  outros sistemas. Neste projeto, foi criado um servidor para fornecer dados de sensores quando solicitados por um ou mais clientes.
- Um socket é uma porta de entrada para processos de comunicação, isto é, ele permite que dois processos distintos se comuniquem e, por consequência, geram determinado resultado. Neste projeto foi utilizado sockets para construção do servidor citado acima.
- Um cliente pode ser visto como um sistema computacional que consome dados de um ou mais servidores. Neste projeto, o cliente foi uma aplicação front-end, isto é uma interface gráfica, que solicitava dados dos sensores e enviava comandos para estes através do broker citado anteriormente, que por consequência também atuou como cliente.
- Um message broker é um software que possibilita que aplicativos, sistemas e serviços se comuniquem e troquem informações. O message broker faz isso convertendo mensagens entre protocolos de mensagens formais. Isso permite que serviços interdependentes "conversem" uns com os outros diretamente, mesmo que tenham sido criados em linguagens diferentes ou tenham sido implementados em plataformas diferentes. Neste projeto o message broker foi utilizado para intermediar a troca de mensagens entre a interface e o dispositivo virtual. Permitindo assim, o desacoplamento entre as aplicações.
- Uma API REST (também chamada de API RESTful ou web API RESTful) é uma interface de programação de aplicativos (API) que segue os princípios de design do estilo arquitetônico de transferência de estado representacional (REST). A API Rest foi utilizada no problema para auxílio da construção do broker.
- Containers são ambientes isolados que podem ser instalados em computadores e serem utilizados para seus devidos fins de forma que não é necessário instalar o serviço dentro do computador como uma aplicação, então caso um container dê problema, como ele é um ambiente isolado ele pode ser deletado e uma outra instância deste container pode ser reinstalado.
- Docker é um software open source que permite a criação de container e armazenamento dos mesmo em repositórios na internet, mas também que faz a virtualização em nível de sistema operacional de suas aplicações em seus contêineres nos computadores facilitando a instalação e remoção sem afetar a máquina física caso seja necessário.

## Metodologia
### Diagrama de alto nível do projeto com um exemplo da troca de mensagem entre os sistemas
![Diagrama do projeto](https://github.com/Joanderson90/communication-between-different-apps/blob/main/diagrama_projeto.png)
O diagrama acima mostra o fluxo da troca de mensagem entre a interface, broker e dispositivo virtual, além de destacar um exemplo de mensagem. Nesta representação, podemos notar que a comunicação se inicia com a interface realizando uma requisição HTTP para o broker, este por sua vez recebe a mensagem e realiza a tradução desta para ser enviada para o servidor, este por sua vez captura a requisição, faz uma ação vinculada a mensagem e devolve uma resposta para o broker que por fim devolve a resposta para a interface. Nas próximas seções irá ser destacado a comunicação entre cada nó(sistema) presente no diagrama.

#### Comunicação Interface e Broker
Esta comunicação é feita através do protocolo HTTP, de forma que a interface realiza uma requisição para a API REST que foi construída o broker. A requisição pode ser uma GET ou POST. Irá ser um GET quando a interface necessitar obter os dados de um sensor específico e, um POST quando a interface precisar enviar um comando para um determinado sensor. Como os sensores não possuem capacidade para rodar em servidores HTTP, se fez necessário a utilização do broker para viabilizar a comunicação entre a interface e os servidores, que na arquitetura deste projeto também simula os sensores.

#### Comunicação Broker e Dispositivo Virtual
Neste caso, a comunicação é feita através do protocolo TCP e UDP. Para a implementação desse protocolo, foram utilizados os sockets nativos da linguagem Java.  O protocolo TCP foi utilizado para envio de comandos para o sensor, tal como, ligar, desligar e alterar a sensibilidade. Isso porque, o protocolo TCP é um protocolo confiável com relação ao envio e recebimento dos dados, o que o torna factível para troca de mensagens que envolvem comandos. Já o protocolo UDP, foi utilizado para envio dos dados do sensor para o broker, quando solicitados. Portanto, o broker atuou como cliente nesta comunicação e o dispositivo virtual como servidor, de forma que quando a troca de mensagem envolve envio de comandos, o broker se comunica com  servidor através do protocolo TCP, já quando envolve envio dos dados a comunicação foi feita através do protocolo UDP. Ademais, foi elaborado um protocolo de comunicação específico para realizar a tradução das mensagens entre estes dois sistemas.

#### Protocolo de comunicação
Além dos protocolos TCP e UDP utilizados para realizar a comunicação citada na seção anterior, foi criado um protocolo para tradução das mensagens presente na comunicação entre o broker e dispositivo virtual, como no exemplo abaixo:
##### Requisição feita pelo broker para o dispositivo virtual:
- ```GET DATA,SOUND```
##### Resposta do dispositivo virtual: 
- ```10.0,70.0,true```

No exemplo acima, o broker envia uma mensagem, que corresponde a uma string, esta é separada por vírgula, onde o primeiro elemento antes da vírgula corresponde a uma ação que será realizada sobre um determinado sensor, ação essa,que neste caso, corresponde a geração/obtenção dos dados, já o segundo elemento após a vírgula identifica um sensor específico, que será alvo da ação citada anteriormente. Com relação à resposta enviada pelo dispositivo virtual, o primeiro elemento é o valor do dado gerado pelo sensor, o segundo elemento após a vírgula equivale a sensibilidade pela qual o dado foi gerado, por fim, temos um valor que pode ser “true”, ou “false”, este corresponde ao estado do sensor, isto é, ligado(true) ou desligado(false).
De modo geral, as mensagens foram separadas por vírgula, onde o primeiro elemento corresponde a ação que será realizada sobre o sensor e o segundo elemento é o sensor em específico.

