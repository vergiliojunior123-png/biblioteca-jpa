CREATE TABLE Aluno (
    matriculaAluno INT PRIMARY KEY,
    nome VARCHAR(255)
);

CREATE TABLE Publicacao (
    codigoPub INT PRIMARY KEY,
    titulo VARCHAR(255),
    ano INT,
    autor VARCHAR(255),
    tipo VARCHAR(255)
);

CREATE TABLE Emprestimo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dataEmprestimo DATE,
    dataDevolucao DATE,
    matricula_aluno INT,
    codigo_pub INT,
    CONSTRAINT fk_emprestimo_aluno
        FOREIGN KEY (matricula_aluno) REFERENCES Aluno(matriculaAluno),
    CONSTRAINT fk_emprestimo_publicacao
        FOREIGN KEY (codigo_pub) REFERENCES Publicacao(codigoPub)
);
