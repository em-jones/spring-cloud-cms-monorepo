/* eslint-disable no-plusplus,no-param-reassign */
import moment from 'moment';
import fp from 'lodash/fp';

export class Post {
  static archive({ posts }) {
    const archive = {};
    // year, dates, posts[] {2017: []}
    Object.values(posts)
      .map(({ createdAt, id, title, tags }) => {
        const year = moment(createdAt).format('YYYY');
        const day = moment(createdAt).format('ddd Do');
        return {
          year, day, id, title, tags,
        };
      })
      .forEach(({ year, day, id, title, tags }) => {
        if (!archive[year]) {
          archive[year] = [];
        }
        archive[year].push({ id, title, day, tags });
      });
    return archive;
  }

  static sorter(itemA, itemB) {
    if (itemA.order > itemB.order) {
      return -1;
    } else if (itemA.order === itemB.order) {
      return 0;
    }
    return 1;
  }

  static menu(items) {
    const menu = {};

    return Object.values(items).sort(Post.sorter).forEach((item) => {
      if (!menu[item.category]) {
        menu[item.category.id] = {
          name: item.category.name,
          items: [],
        };
        menu[item.category.id].items.push(item);
      }
    });
  }

  static printMenu(sortedItems) {
    const menuCategories = [{
      id: '1092jawoijfw',
      name: 'Category',
      order: 3,
    }];

    const sortedCategories = menuCategories.sort(Post.sorter);
    sortedCategories.forEach((category) => {
      // add category title to view by category.name
      if (category) {
        sortedItems.sort();
      }
      // add every item in category to category's block by sortedItems[category.id].forEach()
    });
  }
}

export class Tags {
  static list({ posts }) {
    return this.select({ posts }, true)
      .reduce((cum, { name, id }) => {
        // eslint-disable-next-line no-shadow
        const index = cum.map(tag => tag.name).indexOf(name);
        if (index < 0) {
          return [...cum, { name, id, count: 1 }];
        }
        return cum.map((tag) => {
          if (tag.name === name) {
            return { ...tag, count: tag.count++ };
          }
          return tag;
        });
      }, []);
  }
  static select({ posts }, needCount) {
    const tagArrays = Object.values(posts)
      .map(({ tags }) => tags)
      .reduce((cum, item) => [...cum, ...item], []);
    let sortedTags;
    if (needCount) {
      sortedTags = fp.compose(
        fp.sortBy('name'));
    } else {
      sortedTags = fp.compose(
        fp.sortBy('name'),
      );
    }
    return sortedTags(tagArrays);
  }
}

export class Content {
  static list(contentList) {
    return contentList;
  }
}

export default {
  Post, Content,
};

