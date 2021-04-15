package chess.controller.web;

import chess.domain.game.Game;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessController {
    private final ChessService chessService;

    public WebChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void run() {
        get("/", this::welcomePage);
        get("/start", this::start);
        post("/chess", this::move);
        get("/load", this::load);
    }

    private Object welcomePage(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.html"));
    }

    private Object start(Request request, Response response) {
        return chessService.start();
    }

    private Object move(Request request, Response response) {
        String command = request.queryParams("command");
        return chessService.move(command);
    }

    private Object load(Request request, Response response) {
        return chessService.load();
    }
}
