[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/3TAzxFga)
# projeto-android-nativo-22005813-22004525

## Trabalho realizado por:
```
José Sobral a22006813
Rodrigo Amaro a22004525
```
## 0. Video:
https://www.youtube.com/watch?v=VMPCK7XvTd4 <br>

## 1. Requisitos:

### &emsp; 1.1. Dashboard:
<p float="left">
  <img src="https://github.com/ULHT-CM-2022-23/projeto-android-nativo-22005813-22004525/blob/main/images/dashboard.png" width="270" height="600">
  <img src="https://github.com/ULHT-CM-2022-23/projeto-android-nativo-22005813-22004525/blob/main/images/drawer.png" width="270" height="600">
</p>
<br>
&emsp; Na aplicação inteira pode ser visto o drawer da imagem á direita, com navegação para os ecrãs de Dashboard, Registo do filme, Filmes, Mapa e Cinemas.
<br>
&emsp; No Dashboard podemos ver indicações de numero de filmes vistos, que é um contador com todos os filmes registados na aplicação. Podemos também ver 
que existe uma categoria favorita, que indica a categoria de filmes que o utilizador mais viu. Tem também um botão de redirecionamento para o registo de filmes e
uma preview do mapa, que ao ser clicado também irá redirecionar para o mapa.

### &emsp; 1.2. Apresentação dos filmes:
<img src="https://github.com/ULHT-CM-2022-23/projeto-android-nativo-22005813-22004525/blob/main/images/filmes.png" width="270" height="600">
<img src="https://github.com/ULHT-CM-2022-23/projeto-android-nativo-22005813-22004525/blob/main/images/filmes_rot.png" width="600" height="270">
<br>
&emsp; Na apresentação dos filmes podemos ver uma lista de filmes, que contem o titulo do filme, a data do registo desse filme e o rating que o utilizador lhe deu.
Podemos também ver que quando o ecrã é rodado, aparece as observações do registo do filme.

### &emsp; 1.3. Detalhe do filme:
<p float="left">
  <img src="https://github.com/ULHT-CM-2022-23/projeto-android-nativo-22005813-22004525/blob/main/images/detalhes_1.png" width="270" height="600">
  <img src="https://github.com/ULHT-CM-2022-23/projeto-android-nativo-22005813-22004525/blob/main/images/detalhes_2.png" width="270" height="600">
</p>
<br>
&emsp; No ecrã de detalhes do filme podemos ver que está presente informação como o nome do filme, a categoria, a data de lançamento, o rating do IMDB, o link do IMDB,
o plot do filme e os user ratings com o cinema onde foi visto, o rating do utilizador, a data do registo, as observações do utilizador e as fotos tiradas pelo utilizador.

### &emsp; 1.4. Pesquisa de filmes por voz:
<img src="https://github.com/ULHT-CM-2022-23/projeto-android-nativo-22005813-22004525/blob/main/images/microfone.png" width="270" height="600">
<br>
&emsp; Na pesquisa de filmes por voz podemos ver um dialog com o countdown e um botão de parar de pesquisar.

### &emsp; 1.5. Registo de filmes:
<img src="https://github.com/ULHT-CM-2022-23/projeto-android-nativo-22005813-22004525/blob/main/images/registo.png" width="270" height="600">
<br>
&emsp; No registo de filmes podemos ver um formulário com campos para o nome do filme, o cinema onde foi visto, o rating, a data de registo, fotos e observações.
Os filmes que podem ser colocados neste formulário são o "John Wick", "Breaking Bad" e "Insidious 5" (Filmes hardcoded). Os cinemas que podem ser colocados neste
formulário são o "Cinema Ideal", "Cinema City Campo Pequeno" e "Fórum Montijo".

### &emsp; 1.6. Suporte multi-idioma:
<p float="left">
  <img src="https://github.com/ULHT-CM-2022-23/projeto-android-nativo-22005813-22004525/blob/main/images/prompt_1.png" width="260" height="348">
  <img src="https://github.com/ULHT-CM-2022-23/projeto-android-nativo-22005813-22004525/blob/main/images/prompt_2.png" width="260" height="452">
</p>
<br>
&emsp; Aqui encontra-se os prompts usados para traduzir as strings para espanhol. Dado que os dois membros do grupo traduziram sozinhos as strings para inglês.

### &emsp; 1.7. Apresentação do mapa:
<img src="https://github.com/ULHT-CM-2022-23/projeto-android-nativo-22005813-22004525/blob/main/images/mapa.png" width="270" height="600">
<br>
&emsp; Este é o ecrã do mapa, que se encontra na aplicação mas será realizado na próxima entrega.

### &emsp; 1.8. Extra:
<img src="https://github.com/ULHT-CM-2022-23/projeto-android-nativo-22005813-22004525/blob/main/images/cinemas.png" width="270" height="600">
<br>
&emsp; Na componente extra o grupo decidiu realizar um ecrã com a lista dos cinemas (até agora só os que se encontram hardcoded) do json. Esta lista apresenta todos os
cinemas (hardcoded) com as informações como horário, com indicação que se encontra fechado (bola vermelha) ou aberto (bola verde), morada do cinema e rating médio do 
cinema. A lista apresentada neste ecrã foi desenvolvida recorrendo ao uso de um adapter.

## 2. Lógica de negócio:
&emsp; Nesta secção iremos explicar os models criados pelo grupo no desenvolvimento da aplicação.

```
Classe Cinema:
  - Atributos:
    * id - Int,
    * name - String,
    * provider - String,
    * latitude - Double,
    * longitude - Double,
    * address - String,
    * postCode - String,
    * county - String,
    * ratings - List<Pair<String, Int>>,
    * openingHours - List<Triple<String, String, String>>,
    * logoUrl - String,
    * photos - List<String>; 
  - Métodos: N/A
```

```
Classe FilmeInfo:
  - Atributos:
    * nome - String,
    * imagemCartaz - String,
    * genero - List<String>,
    * sinopse - String,
    * dataLancamento - String,
    * avaliacaoIMDB - Double,
    * linkIMDB - String; 
  - Métodos: N/A
```

```
Classe Filme:
  - Atributos:
    * info: FilmeInfo,
    * cinema: Cinema,
    * avaliacaoUtilizador: Int,
    * dataVisto: Date,
    * fotos: MutableList<String>,
    * observacoes: String,
    * uuid: String; 
  - Métodos: N/A
```

```
Objeto RegistoLogica:
  - Atributos: N/A
  - Métodos:
    * loadCinemas(): void
    * mostWatchedCategory(): String
    * countFilmes(): Int
    * addFilme(filme: Filme): void
    * itemsFilme(): List<String>
    * itemsCinema(): List<String>
    * validForm(nome: String?,cinema: String?,avaliacao: String?,data: String?,observacao: String?): Boolean
    * validObservacao(observacao: String?): Boolean
    * validCinema(nome: String?): Boolean
    * validFilme(nome: String?): Boolean
    * validRating(avaliacao: String?): Boolean
    * encodeBitmapToBase64(bitmap: Bitmap): String
    * decodeBase64ToImageView(base64String: String): Bitmap
    * getFilmeById(uuid: String): Filme?
    * buildHorario(cinema: Cinema): String
    * isOpen(cinema: Cinema): Boolean
```

## 3. Fontes de informação:
https://developer.android.com/training/camera/camera-intents <br>
https://developer.android.com/training/camera-deprecated/photobasics <br>
https://m3.material.io/ <br>
https://m2.material.io/ <br>
https://www.youtube.com/watch?v=xTK81wzyBPw&list=PL50rZONmv8ZRsWj0L3rvSicPSEJl6sQ40 <br>
<br>
&emsp; Foi utilizada a documentação do android extensivamente durante o decorrer do desenvolvimento, assim como a documentação do material.io, m3 para pesquisar componentes
e o m2 para ver a implementação do mesmo. <br>
&emsp; Foi também usada a série do youtube do Bruno Santos, em conjunto com as fichas na aula, para a aprendizagem da técnologia android e para referência ao longo do
desenvolvimento do projeto. <br>


## 4. Auto-Avaliação:
&emsp; O grupo auto-avalia-se com nota de 18 valores.
