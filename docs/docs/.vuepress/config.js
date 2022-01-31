module.exports = {
  lang: 'zh-CN',
  title: 'Mi Translator',
  head: [['link', { rel: 'icon', href: '' }]],
  description: 'Enhance mi translator experience.',

  themeConfig: {
    logo: null,
    navbar: [
      {
        text: 'GitHub',
        link: 'https://github.com/lz233/MiTranslatorPlus',
      },
    ],
    // 侧边栏对象
    // 不同子路径下的页面会使用不同的侧边栏
    sidebar: "auto",
  },
  dest: 'public',
}