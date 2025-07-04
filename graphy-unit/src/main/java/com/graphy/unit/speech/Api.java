package com.graphy.unit.speech;

import cn.hutool.core.util.StrUtil;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class Api {

    public static void main(String[] args) throws Exception {
        Api.speech("D:\\project\\own-pro\\graphy\\window-code\\资源文件\\airing-first.mp3", "请，C001号，到五号窗口。");
    }

    /**
     * 播放声音
     *
     * @param file 支持本地文件或者http://网络请求文件
     */
    private static void playVoice(String file) {
        BufferedInputStream bufferedInputStream = null;
        FileInputStream fileInputStream = null;
        InputStream inputStream = null;
        try {
            if (!StrUtil.hasEmpty(file)) {
                if (file.startsWith("http://")) {
                    URL url = new URL(file);
                    inputStream = url.openStream();
                    bufferedInputStream = new BufferedInputStream(inputStream);
                } else if (new File(file).exists()) {
                    //创建一个输入流
                    fileInputStream = new FileInputStream(file);
                    //创建一个缓冲流
                    bufferedInputStream = new BufferedInputStream(fileInputStream);
                    fileInputStream.close();
                }

                if (bufferedInputStream != null) {
                    //创建播放器对象，把文件的缓冲流传入进去
                    Player player = new Player(bufferedInputStream);
                    //调用播放方法进行播放
                    player.play();
                }

            }
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (Exception err) {

            }
            try {
                if (fileInputStream != null) fileInputStream.close();
            } catch (Exception err) {

            }
            try {
                if (bufferedInputStream != null) bufferedInputStream.close();
            } catch (Exception err) {

            }
        }

    }

    /**
     * 语音转文字并播放
     *
     * @param file 支持本地文件或者http://网络请求文件
     * @param text
     */

    public static void speech(String file, String text) throws Exception {
        playVoice(file);
        ActiveXComponent ax = null;
        try {
            ax = new ActiveXComponent("Sapi.SpVoice");
            // 运行时输出语音内容
            Dispatch spVoice = ax.getObject();
            // 音量 0-100
            ax.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            ax.setProperty("Rate", new Variant(-2));
            // 执行朗读
            Dispatch.call(spVoice, "Speak", new Variant(text));
            spVoice.safeRelease();
            ax.safeRelease();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
