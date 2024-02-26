using Restaurante.Models;

namespace Restaurante.Service
{
    public class PersonalRespositorio : IPersonal
    {
        private Conexion conex = new Conexion();

        public void add(TaPersonal taPersonal)
        {
              conex.TaPersonals.Add(taPersonal);
            conex.SaveChanges();
        }

        public void eleminar(int id)
        {
             conex.TaPersonals.Remove(ObtenerPorid(id));    
            conex.SaveChanges();    
        }

        public IEnumerable<TaPersonal> GetPersonal()
        {
            return conex.TaPersonals;
        }

        public void modificar(TaPersonal taPersonal)
        {
            var obj = conex.TaPersonals.Where(tpro => tpro.IdPersonal == taPersonal.IdPersonal)
            .FirstOrDefault();

            obj.NombrePersonal = taPersonal.NombrePersonal;
            obj.Permisos = taPersonal.Permisos;
            obj.TaPedidos = taPersonal.TaPedidos;
         
            conex.SaveChanges();
        }

        public TaPersonal ObtenerPorid(int id)
        {
            return conex.TaPersonals.Where(tpro => tpro.IdPersonal== id).FirstOrDefault();
        }
    }
}
