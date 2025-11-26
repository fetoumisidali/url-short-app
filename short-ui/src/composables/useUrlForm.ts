import { reactive } from "vue";
import { useShortUrlStore } from "../stores/url.store";
import { urlSchema, type UrlFormData } from "../schema/url.schema";
import { useCreateShortUrl } from "../query/url.query";

export const useUrlForm = () => {
  const { addUrl, getExistOriginalUrl } = useShortUrlStore();
  const { mutate, isPending } = useCreateShortUrl();
  const toast = useToast();

  const form = reactive<UrlFormData>({
    url: "",
  });

  function handleSubmit() {
    const result = urlSchema.safeParse(form);
    if (!result.success) {
      toast.add({
        title: "Invalid URL",
        description: "please enter a valid url",
        icon: "i-lucide-alert-circle",
        color: "error",
      });
      return;
    }
    if (getExistOriginalUrl(form.url)) {
      toast.add({
        title: "Url already exists",
        description: "the url you enter is already exists",
        icon: "i-lucide-info",
        color: "info",
      });
      return;
    }
    mutate(form, {
      onError: () => {
        toast.add({
          title: "Something went wrong",
          description: "please try again later",
          icon: "i-lucide-shield-x",
          color: "error",
        });
      },
      onSuccess: (data) => {
        addUrl(data.shortUrl, form.url);
        form.url = "";
        toast.add({
          title: "Success",
          description: "Your short url has been created successfully",
          icon: "i-lucide-badge-check",
        });
      },
    });
  }

  return {
    form,
    handleSubmit,
    isPending,
  };
};
