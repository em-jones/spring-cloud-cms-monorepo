//actions & mutations
export default {
  cms: {
    post: {
      //actions & mutations
      update: 'cms/post/update',
      create: 'cms/post/create',
      hide: 'cms/post/hide',
      set: 'cms/post/set',
      //getters
      view: 'cms/post/view',
      filter: 'cms/post/filter',
      list: 'cms/post/list',
      //sub-functionality
      paginate: {
        //actions & mutations
        nextPage: 'nextPage',
        prevPage: 'prevPage',

        //getters
        total: 'total',
        totalPages: 'totalpages',
        page: 'page',
      },
      archive: 'cms/post/archive',
    },
    photo: {
      //actions & mutations
      add: 'cms/photo/add',
    },
    tags: {
      list: 'cms/tags/list',
      select: 'cms/tags/select',
    },
    content: {
      //actions & mutations
      update: 'cms/content/update',
      edit: 'cms/content/edit',
      del: 'cms/content/delete',
      create: 'cms/content/create',
      save: 'cms/content/save',
      list: 'cms/content/list',
      view: 'cms/content/view',
      set: 'cms/content/set',
      loaded: 'cms/content/loaded',
      paginate: {
        nextPage: 'cms/content/nextPage',
        prevPage: 'cms/content/prevPage',
      }
    },
  },
  user: {
    login: 'user/login',
    logout: 'user/logout',
    refresh: 'user/refresh',
    load: 'user/load',
    init: 'user/init',
  }
}
