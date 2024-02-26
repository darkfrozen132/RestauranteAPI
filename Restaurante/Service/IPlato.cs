using Restaurante.Models;

namespace Restaurante.Service
{
    public interface IPlato
    {

        IEnumerable<TaPlato> GetPlato();

        TaPlato ObtenerPorid(int id);

        void add(TaPlato taPlato);

        void modificar(TaPlato  taPlato);

        void eleminar(int id);



    }
}
