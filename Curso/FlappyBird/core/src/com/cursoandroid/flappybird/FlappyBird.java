package com.cursoandroid.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture[] passaros;
    private Texture fundo;

    private Texture canoBaixo;
    private Texture canoTopo;

    private Texture gameOver;

    // Configurações
	private float larguraDispositvo;
	private float alturaDispositivo;

	private Random numeroRandomico;

	private float variacao = 0;
	private float velocidadeQueda = 0;
	private float posicaoInicialVertical;

	private float posicaoMovimentoCanoHorizontal;
	private float espacoEntreCanos;

	private float deltaTime;
	private float alturaEntreCanosRandomico;

	private  int estadoJogo = 0; // 0 -> jogo não iniciado; 1 -> jogo iniciado; 2 -. game over

    private BitmapFont fonte;
    private BitmapFont mensagem;
    private int pontuacao = 0;
    private boolean marcouPonto = false;

    private Circle passaroCirculo;
    private Rectangle canoTopoRetangulo;
    private Rectangle canoBaixoRetangulo;

    // Cãmera
    private OrthographicCamera camera;
    private Viewport viewport;
    private final float VITUAL_WIDTH = 768;
    private final float VITUAL_HEIGHT = 1024;

	@Override
	public void create () {
        batch = new SpriteBatch();
        numeroRandomico = new Random();
        fonte = new BitmapFont();
        fonte.setColor(Color.WHITE);
        fonte.getData().setScale(6);

        mensagem = new BitmapFont();
        mensagem.setColor(Color.WHITE);
        mensagem.getData().setScale(3);

        passaroCirculo = new Circle();

        passaros = new Texture[3];
        passaros[0] = new Texture("passaro1.png");
        passaros[1] = new Texture("passaro2.png");


        fundo = new Texture("fundo.png");
        canoBaixo = new Texture("cano_baixo.png");
        canoTopo = new Texture("cano_topo.png");

        gameOver = new Texture("game_over.png");

        // Configuração da câmera
        camera = new OrthographicCamera();
        camera.position.set(VITUAL_WIDTH / 2, VITUAL_HEIGHT / 2, 0);
        viewport = new StretchViewport(VITUAL_WIDTH, VITUAL_HEIGHT, camera);


        larguraDispositvo = VITUAL_WIDTH;
        alturaDispositivo = VITUAL_HEIGHT;

        posicaoInicialVertical = alturaDispositivo / 2;
        posicaoMovimentoCanoHorizontal = larguraDispositvo;
        espacoEntreCanos = 300;
	}

	@Override
	public void render () {

	    camera.update();

	    // limpar frames anteriores
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        deltaTime = Gdx.graphics.getDeltaTime();
        variacao += deltaTime * 10;

        if (variacao > 2) {
            variacao = 0;
        }

	    if (estadoJogo == 0){
            if (Gdx.input.justTouched()){
	            estadoJogo = 1;
            }
        } else {

            velocidadeQueda++;
            if (posicaoInicialVertical > 0 || velocidadeQueda < 0) {
                posicaoInicialVertical -= velocidadeQueda;
            }

	        if (estadoJogo == 1){
                posicaoMovimentoCanoHorizontal -= deltaTime * 200;

                if (Gdx.input.justTouched()) {
                    velocidadeQueda = -15;
                }

                if (posicaoMovimentoCanoHorizontal < -canoTopo.getWidth()) {
                    posicaoMovimentoCanoHorizontal = larguraDispositvo;
                    alturaEntreCanosRandomico = numeroRandomico.nextInt(400) - 200;
                    marcouPonto = false;
                }

                if (posicaoMovimentoCanoHorizontal < 120) {
                    if (!marcouPonto) {
                        pontuacao++;
                        marcouPonto = true;
                    }
                }
            } else { // tela de game over

                if (Gdx.input.justTouched()){
                    estadoJogo = 0;
                    pontuacao = 0;
                    velocidadeQueda = 0;
                    posicaoInicialVertical = alturaDispositivo / 2;
                    posicaoMovimentoCanoHorizontal = larguraDispositvo;
                }
            }
        }

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(fundo, 0, 0, larguraDispositvo, alturaDispositivo);
        batch.draw(canoTopo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomico);
        batch.draw(canoBaixo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + alturaEntreCanosRandomico);
        batch.draw(passaros[(int) variacao], 120, posicaoInicialVertical);
        fonte.draw(batch, String.valueOf(pontuacao), larguraDispositvo / 2, alturaDispositivo - 50);

        if (estadoJogo == 0){
            mensagem.draw(batch, "Toque na tela para Iniciar", larguraDispositvo/2 - 220, alturaDispositivo / 2 + 200);
        } else {
            if (estadoJogo == 2) {
                batch.draw(gameOver, larguraDispositvo / 2 - gameOver.getWidth() / 2, alturaDispositivo / 2);
                mensagem.draw(batch, "Toque na tela para Reiniciar", larguraDispositvo / 2 - 220, alturaDispositivo / 2 - gameOver.getHeight() / 2);
            }
        }

        batch.end();

        passaroCirculo.set(120 + passaros[0].getWidth()/2, posicaoInicialVertical + passaros[0].getHeight()/2, passaros[0].getWidth()/2);
        canoBaixoRetangulo = new Rectangle(
                posicaoMovimentoCanoHorizontal,alturaDispositivo / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + alturaEntreCanosRandomico,
                canoBaixo.getWidth(), canoBaixo.getHeight()
        );
        canoTopoRetangulo = new Rectangle(
                posicaoMovimentoCanoHorizontal,alturaDispositivo / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomico,
                canoTopo.getWidth(), canoTopo.getHeight()
        );

        // Colisão
        if (Intersector.overlaps(passaroCirculo, canoBaixoRetangulo) || Intersector.overlaps(passaroCirculo, canoTopoRetangulo)
                || posicaoInicialVertical <= 0 || posicaoInicialVertical >= alturaDispositivo){
            estadoJogo = 2; // game over
        }
	}

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
