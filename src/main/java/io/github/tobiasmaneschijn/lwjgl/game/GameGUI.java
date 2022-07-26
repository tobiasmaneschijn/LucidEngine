package io.github.tobiasmaneschijn.lwjgl.game;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.ImGuiStyle;
import imgui.ImVec4;
import imgui.flag.ImGuiCol;
import io.github.tobiasmaneschijn.lwjgl.engine.hud.IGuiLayer;

public class GameGUI implements IGuiLayer {

    private final DummyGame game;

    public GameGUI(DummyGame game) {
        this.game = game;
    }

    private void setColor(ImGuiStyle style, int colorId, ImVec4 color) {
        style.setColor(colorId, color.x, color.y, color.z, color.w);
    }

    private void setDefaultStyle() {
        // Colors

        ImVec4 blackSemi = new ImVec4(0.00f, 0.00f, 0.00f, 0.94f);

        ImVec4 black = new ImVec4(0.00f, 0.00f, 0.00f, 1.00f);

        ImVec4 red = new ImVec4(0.23f, 0.16f, 0.16f, 1.00f);

        ImVec4 beige = new ImVec4(0.78f, 0.62f, 0.51f, 1.00f);

        ImVec4 darkGrey = new ImVec4(0.24f, 0.24f, 0.24f, 1.00f);

        ImVec4 greenGrey = new ImVec4(0.148f, 0.168f, 0.153f, 1.00f);

        ImVec4 white = new ImVec4(1.00f, 1.00f, 1.00f, 1.00f);

        ImVec4 darkerGrey = new ImVec4(0.03f, 0.03f, 0.03f, 1.00f);

        ImVec4 grey = new ImVec4(0.41f, 0.41f, 0.41f, 1.00f);

        ImVec4 green = new ImVec4(0.21f, 0.27f, 0.27f, 1.00f);


        ImGuiStyle style = ImGui.getStyle();

        setColor(style, ImGuiCol.Text, beige);

        setColor(style, ImGuiCol.TextDisabled, white);

        setColor(style, ImGuiCol.WindowBg, black);

        setColor(style, ImGuiCol.ChildBg, black);

        setColor(style, ImGuiCol.PopupBg, black);

        setColor(style, ImGuiCol.Border, beige);

        setColor(style, ImGuiCol.BorderShadow, black);

        setColor(style, ImGuiCol.FrameBg, red);

        setColor(style, ImGuiCol.FrameBgHovered, darkerGrey);

        setColor(style, ImGuiCol.FrameBgActive, black);

        setColor(style, ImGuiCol.TitleBg, black);

        setColor(style, ImGuiCol.TitleBgActive, black);

        setColor(style, ImGuiCol.TitleBgCollapsed, black);

        setColor(style, ImGuiCol.MenuBarBg, red);

        setColor(style, ImGuiCol.ScrollbarBg, black);

        setColor(style, ImGuiCol.ScrollbarGrab, red);

        setColor(style, ImGuiCol.ScrollbarGrabHovered, darkerGrey);

        setColor(style, ImGuiCol.ScrollbarGrabActive, grey);

        setColor(style, ImGuiCol.CheckMark, beige);

        setColor(style, ImGuiCol.SliderGrab, beige);

        setColor(style, ImGuiCol.SliderGrabActive, beige);

        setColor(style, ImGuiCol.Button, red);

        setColor(style, ImGuiCol.ButtonHovered, darkerGrey);

        setColor(style, ImGuiCol.ButtonActive, red);

        setColor(style, ImGuiCol.Header, red);

        setColor(style, ImGuiCol.HeaderHovered, darkerGrey);

        setColor(style, ImGuiCol.HeaderActive, black);

        setColor(style, ImGuiCol.Separator, beige);

        setColor(style, ImGuiCol.SeparatorHovered, darkerGrey);
        setColor(style, ImGuiCol.SeparatorActive, beige);

        setColor(style, ImGuiCol.ResizeGrip, black);

        setColor(style, ImGuiCol.ResizeGripHovered, darkerGrey);

        setColor(style, ImGuiCol.ResizeGripActive, black);

        setColor(style, ImGuiCol.Tab, red);

        setColor(style, ImGuiCol.TabHovered, darkerGrey);

        setColor(style, ImGuiCol.TabActive, darkerGrey);

        setColor(style, ImGuiCol.TabUnfocused, black);

        setColor(style, ImGuiCol.TabUnfocusedActive, black);

        setColor(style, ImGuiCol.PlotLines, beige);

        setColor(style, ImGuiCol.PlotLinesHovered, darkerGrey);

        setColor(style, ImGuiCol.PlotHistogram, beige);

        setColor(style, ImGuiCol.PlotHistogramHovered, darkerGrey);

        setColor(style, ImGuiCol.TextSelectedBg, black);

        setColor(style, ImGuiCol.DragDropTarget, beige);

        setColor(style, ImGuiCol.NavHighlight, black);

        setColor(style, ImGuiCol.NavWindowingHighlight, black);

        setColor(style, ImGuiCol.NavWindowingDimBg, black);

        setColor(style, ImGuiCol.ModalWindowDimBg, black);

        ImGuiIO io = ImGui.getIO();
        io.setFontGlobalScale(1.6f);

    }

    @Override
    public void drawGUI() {


        ImGui.begin("Chess Menu");

        if (ImGui.button("Start game")) {
            setDefaultStyle();
        }

        ImGui.end();
    }
}
