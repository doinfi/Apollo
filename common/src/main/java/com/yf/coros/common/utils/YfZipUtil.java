package com.yf.coros.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author lihuaijin
 */
@Slf4j
public class YfZipUtil {

    private static final String UTF_8 = "utf-8";

    public static byte[] compress(String primStr, String... entryName) {
        if (primStr == null || primStr.length() == 0) {
            return null;
        }
        String fileEntryName;
        if (null != entryName && entryName.length > 0) {
            fileEntryName = entryName[0];
        } else {
            fileEntryName = "file";
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ZipOutputStream zout = new ZipOutputStream(out)) {
            zout.putNextEntry(new ZipEntry(fileEntryName));
            zout.write(primStr.getBytes(UTF_8));
            zout.closeEntry();
            zout.finish();
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将压缩并Base64后的字符串进行解密解压
     *
     * @param compressed 待解密字节数组
     * @return 解压出的字符串
     */
    public static String uncompress(byte[] compressed) {
        if (compressed == null) {
            return null;
        }
        String decompressed;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ByteArrayInputStream in = new ByteArrayInputStream(compressed);
             ZipInputStream zin = new ZipInputStream(in)) {

            zin.getNextEntry();
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString(UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            decompressed = null;
        }
        return decompressed;
    }


    public static void toZip(String destFile, String srcStr, String... entryName) throws RuntimeException {
        if (null == destFile) {
            return;
        }
        File f = new File(destFile);
        String fileEntryName;
        if (null != entryName && entryName.length > 0) {
            fileEntryName = entryName[0];
        } else {
            fileEntryName = f.getName();
        }
        try (OutputStream os = new FileOutputStream(f)) {
            toZip(os, srcStr, fileEntryName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void toZip(File dest, String srcStr, String... entryName) throws RuntimeException {
        if (null == dest) {
            return;
        }
        String fileEntryName;
        if (null != entryName && entryName.length > 0) {
            fileEntryName = entryName[0];
        } else {
            fileEntryName = dest.getName();
        }
        try (OutputStream os = new FileOutputStream(dest)) {
            toZip(os, srcStr, fileEntryName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void toZipMkdir(OutputStream out, String entryNameDir) throws RuntimeException {

        try (ZipOutputStream zos = new ZipOutputStream(out)) {
            toZipMkdirNotClose(zos, entryNameDir);
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        }
    }

    public static void toZipMkdirNotClose(ZipOutputStream zos, String entryNameDir) throws RuntimeException {

        try {
            zos.putNextEntry(new ZipEntry(entryNameDir));
            zos.closeEntry();
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        }
    }

    public static void toZip(OutputStream out, String srcStr, String... entryName) throws RuntimeException {
        try (ZipOutputStream zos = new ZipOutputStream(out)) {
            toZipNotClose(zos, srcStr, entryName);
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        }
    }

    public static void toZipNotClose(ZipOutputStream zos, String srcStr, String... entryName) throws RuntimeException {
        long start = System.currentTimeMillis();
        String fileEntryName;
        if (null != entryName && entryName.length > 0) {
            fileEntryName = entryName[0];
        } else {
            fileEntryName = "file";
        }
        try (ByteArrayInputStream bis = new ByteArrayInputStream(srcStr.getBytes())) {
            byte[] buf = new byte[1024];
            zos.putNextEntry(new ZipEntry(fileEntryName));
            int len;
            while ((len = bis.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            zos.closeEntry();
            long end = System.currentTimeMillis();
            log.debug("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        }
    }

    public static void toZip(OutputStream out, File srcFile) throws RuntimeException {
        long start = System.currentTimeMillis();
        try (ZipOutputStream zos = new ZipOutputStream(out)) {
            byte[] buf = new byte[1024];
            zos.putNextEntry(new ZipEntry(srcFile.getName()));
            int len;
            FileInputStream in = new FileInputStream(srcFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            zos.closeEntry();
            in.close();
            long end = System.currentTimeMillis();
            log.debug("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        }
    }

    /**
     * 批量写入多个文件，注意，如果有多层路径，要先在zip内创建路径，再调用本方法
     * @param out 结果输出流
     * @param entryFileNameMap 文件在zip内的路径+名称与文件本地路径名的映射map
     * @throws RuntimeException 失败时抛出异常
     */
    public static void toZip(OutputStream out, Map<String, String> entryFileNameMap) throws RuntimeException {
        try (ZipOutputStream zos = new ZipOutputStream(out)) {
            toZipNotClose(zos, entryFileNameMap);
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        }
    }

    /**
     * 批量写入多个文件，注意，如果有多层路径，要先在zip内创建路径，再调用本方法
     * @param zos 结果输出流
     * @param entryFileNameMap 文件在zip内的路径+名称与文件本地路径名的映射map
     * @throws RuntimeException 失败时抛出异常
     */
    public static void toZipNotClose(ZipOutputStream zos, Map<String, String> entryFileNameMap) throws RuntimeException {
        long start = System.currentTimeMillis();
        try {
            byte[] buf = new byte[1024];
            for (Map.Entry<String, String> entry: entryFileNameMap.entrySet()) {
                String entryName = entry.getKey();
                String entryFileName = entry.getValue();
                File srcFile = new File(entryFileName);
                if (!srcFile.exists()) {
                    log.warn("文件不存在： {}", entryFileName);
                    continue;
                }

                try (FileInputStream in = new FileInputStream(srcFile)) {
                    zos.putNextEntry(new ZipEntry(entryName));
                    int len;
                    while ((len = in.read(buf)) != -1) {
                        zos.write(buf, 0, len);
                    }
                    zos.closeEntry();
                } catch (IOException e) {
                    log.warn("读取文件错误：" + entryFileName, e);
                }
            }
            long end = System.currentTimeMillis();
            log.debug("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        }
    }

    /**
     * 功能:压缩多个文件成一个zip文件
     *
     * @param srcfile：源文件列表
     * @param zipfile：压缩后的文件
     * @author 王志坚
     */
    public static void zipFileList(File[] srcfile, File zipfile) {
        byte[] buf = new byte[1024];
        try {
            //ZipOutputStream类：完成文件或文件夹的压缩
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
            for (int i = 0; i < srcfile.length; i++) {
                FileInputStream in = new FileInputStream(srcfile[i]);
                out.putNextEntry(new ZipEntry(srcfile[i].getName()));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
        } catch (Exception e) {
            log.error("压缩文件出错",e);
        }
    }

    /**
     * 功能:解压缩
     *
     * @param zipfile：需要解压缩的文件
     * @param descDir：解压后的目标目录
     * @author 王志坚
     */
    public static void unZipFile(File zipfile, String descDir) {
        try {
            ZipFile zf = new ZipFile(zipfile);
            for (Enumeration entries = zf.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zf.getInputStream(entry);
                OutputStream out = new FileOutputStream(descDir + zipEntryName);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
            }
        } catch (Exception e) {
            log.error("解压文件出错",e);
        }
    }
}
