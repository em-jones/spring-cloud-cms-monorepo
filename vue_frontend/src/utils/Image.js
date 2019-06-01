import Emitter from 'quill';
import Delta from 'quill-delta';
import { services } from '@/api';


export default {
  /**
   * https://github.com/quilljs/quill/issues/1089
   */
  handleImageUpload(querySelector = 'input.ql-image[type=file]') {
    return function a() {
      let fileInput = this.container.querySelector(querySelector);
      if (fileInput == null) {
        fileInput = document.createElement('input');
        fileInput.setAttribute('type', 'file');
        fileInput.setAttribute('accept', 'image/png, image/gif, image/jpeg, image/bmp, image/x-icon');
        fileInput.classList.add('ql-image');
        fileInput.addEventListener('change', () => {
          if (fileInput.files != null && fileInput.files[0] != null) {
            const reader = new FileReader();
            reader.onload = async () => {
              const [file] = fileInput.files;
              const { data } = await services.cloudinary.upload(file);
              const range = this.quill.getSelection(true);
              this.quill.updateContents(new Delta()
                .retain(range.index)
                .delete(range.length)
                .insert({ image: data.secure_url })
                , Emitter.sources.USER);
              fileInput.value = '';
            };
            reader.readAsDataURL(fileInput.files[0]);
          }
        });
        this.container.appendChild(fileInput);
      }
      fileInput.click();
    };
  },
};

