package Principal.modelos;

import java.util.Date;

public class Devolucao {
    private Livro livro;
    private Date dataDevolucao;

    public Devolucao(Livro livro, Date dataDevolucao) {
        this.livro = livro;
        this.dataDevolucao = dataDevolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    @Override
    public String toString() {
        return "Devolucao{" +
                "livro=" + livro +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }
}
